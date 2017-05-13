package com.gamesys.roulette.dao;

import com.gamesys.roulette.beans.Player;

import java.util.List;

/**
 * DAO for players.
 */
public interface PlayerDao {

    /**
     * Retrieve the list of players (and their statistics if present).
     *
     * @return a list of players
     */
    List<Player> getAll();

    /**
     * Save the specified list of players (and their statistics).
     *
     * @param players a list of players
     */
    void saveAll(List<Player> players);
}
