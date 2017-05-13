package com.gamesys.roulette.beans;

/**
 * This class represents a player.
 */
public class Player {

    private String name;

    private PlayerStatistics statistics;


    public Player(final String name) {
        this(name, new PlayerStatistics());
    }

    public Player(final String name, final PlayerStatistics statistics) {
        this.name = name;
        this.statistics = statistics;
    }

    public Player(final String name, final Money totalWin, final Money totalBet) {

    }

    public String getName() {
        return name;
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }
}
