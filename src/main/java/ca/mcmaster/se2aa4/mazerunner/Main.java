package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeLoader;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.maze.PathValidator;

public class Main {

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", "input", true, "Input file for the maze");
        options.addOption("p", "path", true, "Path to verify");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (!cmd.hasOption("i")) {
                System.err.println("Input file not specified. Use -i flag.");
                return;
            }
            String inputFilePath = cmd.getOptionValue("i");
            Maze maze = MazeLoader.loadMaze(inputFilePath);
            printMaze(maze);

            if (cmd.hasOption("p")) {
                String path = cmd.getOptionValue("p");
                Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
                PathValidator validator = new PathValidator(maze, explorer);
                boolean valid = validator.verifyPath(path);
                System.out.println("The path is " + (valid ? "valid." : "invalid."));
            } else {
                Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
                MazeSolver solver = new MazeSolver(maze, explorer);
                String path = solver.solve();
                System.out.println("Solved path: " + path);
            }
        } catch (ParseException e) {
            System.err.println("Error parsing command line arguments: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading maze: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void printMaze(Maze maze) {
        for (char[] row : maze.getGrid()) {
            System.out.println(new String(row));
        }
    }
}
