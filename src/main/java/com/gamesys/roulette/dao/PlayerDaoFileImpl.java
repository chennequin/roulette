package com.gamesys.roulette.dao;

import com.gamesys.roulette.beans.Money;
import com.gamesys.roulette.beans.Player;
import com.google.common.base.Throwables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A CSV File implementation for players DAO.
 */
@Component
public class PlayerDaoFileImpl implements PlayerDao {

    private static final String CSV_SEPARATOR = ",";

    private String path;

    /**
     * Command line arguments are given by Spring API.
     *
     * @param args application arguments
     */
    @Autowired
    public PlayerDaoFileImpl(final ApplicationArguments args) {
        this.path = args.getSourceArgs()[0];
    }

    @Override
    public List<Player> getAll() {

        try {
            final List<String> lines = Files.readAllLines(Paths.get(path));

            return lines.stream()
                    .map(PlayerDaoFileImpl::stringToPlayer)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void saveAll(List<Player> players) {

        try {
            final List<String> lines = players.stream()
                    .map(PlayerDaoFileImpl::playerToString)
                    .collect(Collectors.toList());

            Files.write(Paths.get(path), lines);

        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    /**
     * Function that converts a csv line to a Player instance.
     *
     * @param line a line of a csv file
     * @return a Player instance
     */
    public static Player stringToPlayer(final String line) {

        final String[] p = line.split(CSV_SEPARATOR);

        if (p.length == 1) {
            return new Player(line.trim());
        }

        return new Player(p[0].trim(),
                Money.pounds(Double.parseDouble(p[1])),
                Money.pounds(Double.parseDouble(p[2])));
    }

    /**
     * Function that converts a player to a csv line.
     *
     * @param player a Player instance
     * @return a csv line
     */
    public static String playerToString(final Player player) {

        final StringBuilder sb = new StringBuilder();

        sb.append(player.getName());

        if (player.getStatistics().getTotalBet().greaterThan(Money.pounds(0))
                || player.getStatistics().getTotalWin().greaterThan(Money.pounds(0))) {

            sb.append(CSV_SEPARATOR);
            sb.append(player.getStatistics().getTotalWin().amount());
            sb.append(CSV_SEPARATOR);
            sb.append(player.getStatistics().getTotalBet().amount());
        }

        return sb.toString();
    }

}
