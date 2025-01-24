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
                logger.info("Attempting to load maze from file: " + inputFilePath);

                // Load the maze
                Maze maze = MazeLoader.loadMaze(inputFilePath);

                // Print the maze
                printMaze(maze);

                // Log successful loading
                logger.info("Maze loaded successfully.");
            } else {
                logger.error("Input file not specified. Use the -i flag to specify the maze file.");
            }
        } catch (ParseException e) {
            logger.error("Error parsing command line arguments: ", e);
        } catch (IOException e) {
            logger.error("Error loading the maze: ", e);
        } catch (Exception e) {
            logger.error("An unexpected error occurred: ", e);
        }
    }

    private static void printMaze(Maze maze) {
        char[][] grid = maze.getGrid();
        for (char[] row : grid) {
            System.out.println(row);
        }
    }
}
