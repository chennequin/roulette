package com.gamesys.roulette.beans.bets;

import com.gamesys.roulette.beans.Money;
import com.gamesys.roulette.beans.Player;

/**
 * This class represents a bet: ODD
 */
public class OddBet extends AbstractBet {

    private static final int GAIN_MULTIPLY = 2;

    /**
     * Constructor.
     *
     * @param player a player
     * @param amount an amount of money
     */
    public OddBet(final Player player, final Money amount) {
        super(player, amount);
    }

    /**
     * Returns an odd bet with the specified amount of money.
     *
     * @param player a player
     * @param amount an amount of money
     * @return an odd bet
     * @throws IllegalArgumentException if amount does not contain a parsable {@code double}.
     */
    public static OddBet of(final Player player, final String amount) {
        try {
            return new OddBet(
                    player,
                    Money.pounds(Double.parseDouble(amount)));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("Invalid bet: %s", amount), ex);
        }
    }

    @Override
    public boolean isWin(int number) {
        return number % 2 != 0;
    }

    @Override
    public Money getWinAmount() {
        return getBetAmount().multiply(GAIN_MULTIPLY);
    }

    @Override
    public String toString() {
        return "ODD";
    }
}
