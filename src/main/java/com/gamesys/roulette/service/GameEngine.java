package com.gamesys.roulette.service;

import com.gamesys.roulette.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Game Engine:
 * - gather bets from players
 * - choose a random number
 * - build immutable results of games
 */
@Component
public class GameEngine {

    @Autowired
    private PlayerDao players;

}
