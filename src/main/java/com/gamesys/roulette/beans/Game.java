package com.gamesys.roulette.beans;

import com.gamesys.roulette.beans.bets.AbstractBet;
import com.google.common.collect.ImmutableCollection;

/**
 * This class represents a game result.
 */
public class Game {

    private int gameNumber;

    /**
     * This collection is immutable.
     * Once the game is generated we want to enforce (freeze) the bets.
     */
    private ImmutableCollection<AbstractBet> bets;

    /**
     * Constructor.
     *
     * @param gameNumber a number of this game
     * @param bets the collection of bets for this game
     */
    public Game(int gameNumber, ImmutableCollection<AbstractBet> bets) {
        this.gameNumber = gameNumber;
        this.bets = bets;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public ImmutableCollection<AbstractBet> getBets() {
        return bets;
    }
}
