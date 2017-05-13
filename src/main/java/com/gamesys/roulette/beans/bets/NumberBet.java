package com.gamesys.roulette.beans.bets;

import com.gamesys.roulette.beans.Money;
import com.gamesys.roulette.beans.Player;
import com.google.common.collect.Range;
import org.springframework.util.Assert;

/**
 * This class represents a bet with a number.
 */
public class NumberBet extends AbstractBet {

    private static final Range<Integer> validBets = Range.closed(1, 36);

    private static final int GAIN_MULTIPLY = 36;

    private int betNumber;

    /**
     * Constructor.
     *
     * @param player    a player
     * @param betNumber a number
     * @param amount    an amount of money
     */
    public NumberBet(final Player player, final int betNumber, final Money amount) {
        super(player, amount);
        Assert.isTrue(validBets.contains(betNumber), "must provide number between 1-36");
        this.betNumber = betNumber;
    }

    /**
     * Returns a bet with the specified number.
     *
     * @param player a player
     * @param number a number
     * @param amount an amount of money
     * @return a bet with the specified number
     * @throws IllegalArgumentException if number does not contain a parsable {@code int}.
     * @throws IllegalArgumentException if amount does not contain a parsable {@code double}.
     */
    public static NumberBet of(final Player player, final String number, final String amount) {
        try {
            return new NumberBet(
                    player,
                    Integer.parseInt(number),
                    Money.pounds(Double.parseDouble(amount)));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("Invalid bet: %s %s", number, amount), ex);
        }
    }

    public int getBetNumber() {
        return betNumber;
    }

    @Override
    public boolean isWin(int gameNumber) {
        return betNumber == gameNumber;
    }

    @Override
    public Money getWinAmount() {
        return getBetAmount().multiply(GAIN_MULTIPLY);
    }

    @Override
    public String toString() {
        return Integer.toString(betNumber);
    }
}
