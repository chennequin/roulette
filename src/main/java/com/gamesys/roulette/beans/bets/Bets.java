package com.gamesys.roulette.beans.bets;

import com.gamesys.roulette.beans.Player;
import org.springframework.util.Assert;

/**
 * A builder class for bets.
 */
public class Bets {

    /**
     * Return a AbstractBet instance from the specified String value.
     *
     * @param bet a string
     * @return a AbstractBet instance
     */
    public static AbstractBet of(final Player player, final String bet, final String amount) {

        Assert.notNull(bet, "Must provide a bet");

        //final String[] split = bet.split(" ");

        //final String userId = split[0];
        //final String type = split[1];
        //final String number = split[2];
        //final String amount = split[3];

        final String cleaned = bet.trim().toLowerCase();

        switch (cleaned) {
            case "even":
                return EvenBet.of(player, amount);
            case "odd":
                return OddBet.of(player, amount);
            default:
                return NumberBet.of(player, bet, amount);
        }
    }


}
