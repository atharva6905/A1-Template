package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeLoader;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.maze.PathValidator;
import ca.mcmaster.se2aa4.mazerunner.maze.RightHandSolver;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Set up Apache CLI options
        Options options = new Options();
        options.addOption("i", "input", true, "Input file for the maze");
        options.addOption("p", "path", true, "Path to verify");

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

                if (cmd.hasOption("p")) {
                    String path = cmd.getOptionValue("p");
                    logger.info("Verifying path: " + path);

                    // Verify the path
                    Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
                    PathValidator validator = new PathValidator(maze, explorer);
                    boolean isValid = validator.verifyPath(path);
                    if (isValid) {
                        logger.info("The path is valid.");
                        System.out.println("The path is valid.");
                    } else {
                        logger.error("The path is invalid.");
                        System.err.println("The path is invalid.");
                    }
                } else {
                    // Solve the maze using the right-hand rule
                    Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
                    MazeSolver solver = new RightHandSolver(maze, explorer);
                    String path = solver.solve();
                    logger.info("Solved path: " + path);
                    System.out.println("Solved path: " + path);
                }
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
            System.out.println(new String(row));
        }
    }
}
