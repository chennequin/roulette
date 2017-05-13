package com.gamesys.roulette.beans.bets;

import com.gamesys.roulette.beans.Money;
import com.gamesys.roulette.beans.Player;
import org.springframework.util.Assert;

/**
 * This abstract class represents a bet and is the base class for all bets.
 */
public abstract class AbstractBet {

    private Player player;

    private Money amount;

    /**
     * Constructor.
     *
     * @param player a player
     * @param amount an amount of money
     */
    public AbstractBet(final Player player, final Money amount) {
        Assert.notNull(amount, "must provide an amount");
        Assert.notNull(player, "must provide a player");
        this.player = player;
        this.amount = amount;
    }

    public Player getPlayer() {
        return player;
    }

    public Money getBetAmount() {
        return amount;
    }

    /**
     * Return true if this bet is a win against the specified number.
     * <p>
     * This method is to be implemented by every types of bet.
     *
     * @param number a number
     * @return true if this bet is a win against the specified number, false otherwise.
     */
    public abstract boolean isWin(int number);

    /**
     * Return the amount of money won in case of that bet wins.
     *
     * @return an amount of money
     */
    public abstract Money getWinAmount();

}
