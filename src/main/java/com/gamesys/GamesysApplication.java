package com.gamesys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for Gamesys Console Roulette.
 */
@SpringBootApplication
public class GamesysApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GamesysApplication.class);

    private GamesysApplication() {
    }

    /**
     * Bootstrap of the application.
     *
     * @param args arguments must contain the players filename
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
            return;
        }
        SpringApplication.run(GamesysApplication.class, args); // NOSONAR
    }

    /**
     * Print usage of this application to the standard output.
     */
    public static void printUsage() {
        LOG.info("USAGE: jar -jar coding-exercise.jar <filename>");
    }
}
