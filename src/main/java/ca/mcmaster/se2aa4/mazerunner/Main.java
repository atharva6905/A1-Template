package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeLoader;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Set up Apache CLI options
        Options options = new Options();
        options.addOption("i", "input", true, "Input file for the maze");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) {
                String inputFilePath = cmd.getOptionValue("i");
                Maze maze = MazeLoader.loadMaze(inputFilePath);
                logger.info("Maze loaded successfully.");
                // For now, just print the maze to verify loading
                printMaze(maze);
            } else {
                logger.error("Input file not specified.");
            }
        } catch (ParseException | IOException e) {
            logger.error("Error parsing command line or loading maze: ", e);
        }
    }

    private static void printMaze(Maze maze) {
        char[][] grid = maze.getGrid();
        for (char[] row : grid) {
            System.out.println(row);
        }
    }
}
