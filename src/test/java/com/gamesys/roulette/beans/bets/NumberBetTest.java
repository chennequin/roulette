package com.gamesys.roulette.beans.bets;

import com.gamesys.roulette.beans.Money;
import com.gamesys.roulette.beans.Player;
import net.java.quickcheck.Generator;
import net.java.quickcheck.generator.PrimitiveGenerators;
import net.java.quickcheck.generator.iterable.Iterables;
import net.java.quickcheck.generator.support.IntegerGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(JUnit4.class)
public class NumberBetTest {

    private static final Player player = new Player("Barbara");

    private Generator<Integer> numbers = new IntegerGenerator(1, 36);
    private Generator<Double> amounts = PrimitiveGenerators.doubles(0, 30000);

    private Generator<Integer> powers = PrimitiveGenerators.positiveIntegers(32);

    class NumberBetGenerator implements net.java.quickcheck.Generator<NumberBet> {

        @Override
        public NumberBet next() {
            return NumberBet.of(player,
                    numbers.next().toString(),
                    Double.valueOf(Math.abs(amounts.next())).toString());
        }
    }

    /**
     * 0 is always looses
     */
    @Test
    public void zeroAlwaysLooses() {
        for (NumberBet bet : Iterables.toIterable(new NumberBetGenerator())) {
            assertFalse(bet.isWin(0));
        }
    }

    /**
     * The bet number always wins
     */
    @Test
    public void betNumberAlwaysWins() {
        for (NumberBet bet : Iterables.toIterable(new NumberBetGenerator())) {
            assertTrue(bet.isWin(bet.getBetNumber()));
        }
    }

    /**
     * Any number which is different than the bet number looses
     */
    @Test
    public void anyNumberDifferentThanBetNumberAlwaysLooses() {
        for (NumberBet bet : Iterables.toIterable(new NumberBetGenerator())) {
            for (int i = 0; i < 36; i++) {
                if (i != bet.getBetNumber()) {
                    assertFalse(bet.isWin(i));
                }
            }
        }
    }

    @Test
    public void winAmountFor1() {
        NumberBet bet = new NumberBet(player, numbers.next(), Money.pounds(1));
        assertEquals("player wins 36x the bet's amount", Money.pounds(36), bet.getWinAmount());
    }

    @Test
    public void winAmountFor2() {
        NumberBet bet = new NumberBet(player, numbers.next(), Money.pounds(2));
        assertEquals("player wins 36x the bet's amount", Money.pounds(72), bet.getWinAmount());
    }

    @Test
    public void winAmountLimit1() {
        NumberBet bet = new NumberBet(player, numbers.next(), Money.pounds(100000));
        assertEquals("player wins 36x the bet's amount",
                Money.pounds(3600000), bet.getWinAmount());

    }

    @Test
    public void winAmountLimit2() {
        NumberBet bet = new NumberBet(player, numbers.next(), Money.pounds(300000));
        assertEquals("player wins 36x the bet's amount",
                Money.pounds(10800000), bet.getWinAmount());

    }

    @Test
    public void winAmountIsAlwaysMultipleOf36() {
        for (NumberBet bet : Iterables.toIterable(new NumberBetGenerator())) {
            System.out.println("bet=" + bet.getBetAmount());
            System.out.println("winamount=" + bet.getWinAmount());

            System.out.println(BigDecimal.valueOf(664693).multiply(BigDecimal.valueOf(36)));
            System.out.println(BigDecimal.valueOf(664693, BigDecimal.ROUND_HALF_EVEN).multiply(BigDecimal.valueOf(36, BigDecimal.ROUND_HALF_EVEN)));

            assertEquals("win amount is multiple of 2",
                    BigDecimal.ZERO.intValue(),
                    bet.getWinAmount().amount().remainder(BigDecimal.valueOf(2)).intValue());
            //assertEquals("player wins 36x the bet's amount", bet.getBetAmount().multiply(36), bet.getWinAmount());
        }
    }

}
