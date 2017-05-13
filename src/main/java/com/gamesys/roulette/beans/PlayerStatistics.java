package com.gamesys.roulette.beans;

/**
 * This class represents a player statistics.
 */
public class PlayerStatistics {

    private Money totalWin;
    private Money totalBet;

    /**
     * Constructor.
     */
    public PlayerStatistics() {
        this(Money.pounds(0), Money.pounds(0));
    }

    /**
     * Constructor.
     *
     * @param totalWin total win of this statistic
     * @param totalBet total bet of this statistic
     */
    public PlayerStatistics(final Money totalWin, final Money totalBet) {
        this.totalWin = totalWin;
        this.totalBet = totalBet;
    }

    /**
     * Increments the totalWin by the specified amount of money.
     *
     * @param amount an amount of money
     */
    public void incrementTotalWin(final Money amount) {
        this.totalWin = totalWin.add(amount);
    }

    /**
     * Increments the totalBet by the specified amount of money.
     *
     * @param amount an amount of money
     */
    public void incrementTotalBet(final Money amount) {
        this.totalBet = totalBet.add(amount);
    }

    public Money getTotalWin() {
        return totalWin;
    }

    public Money getTotalBet() {
        return totalBet;
    }
}
