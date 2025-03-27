package ca.mcmaster.se2aa4.mazerunner.maze;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.command.Command;
import ca.mcmaster.se2aa4.mazerunner.command.MoveForwardCommand;
import ca.mcmaster.se2aa4.mazerunner.command.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.command.TurnRightCommand;
import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;

public class PathValidator {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Explorer explorer;

    public PathValidator(Maze maze, Explorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    public boolean verifyPath(String path) {
        // Check the path from entry to exit
        if (validatePath(path, maze.getEntryX(), maze.getEntryY(), maze.getExitX(), maze.getExitY())) {
            return true;
        }

        // Check the path from exit to entry
        return validatePath(path, maze.getExitX(), maze.getExitY(), maze.getEntryX(), maze.getEntryY());
    }

    private boolean validatePath(String path, int startX, int startY, int endX, int endY) {
        explorer = new Explorer(new Position(startX, startY, 'E'));

        List<Command> commands = parseCommands(path);
        int stepCounter = 0;

        for (Command command : commands) {
            stepCounter++;
            boolean success = command.execute();
            if (!success) {
                logger.error("Path verification failed at step {}", stepCounter);
                return false;
            }
        }

        Position finalPos = explorer.getPosition();
        boolean atGoal = finalPos.getX() == endX && finalPos.getY() == endY;
        if (!atGoal) {
            logger.error("Path complete, but explorer ended at (row={}, col={}) instead of (row={}, col={})",
                    finalPos.getY(), finalPos.getX(), endY, endX);
        }
        return atGoal;
    }

    private List<Command> parseCommands(String path) {
        List<Command> commands = new ArrayList<>();
        int i = 0;

        while (i < path.length()) {
            int count = 0;
            while (i < path.length() && Character.isDigit(path.charAt(i))) {
                count = count * 10 + (path.charAt(i) - '0');
                i++;
            }
            if (count == 0) count = 1;

            if (i >= path.length()) break;
            char move = path.charAt(i++);
            Command cmd = null;

            for (int k = 0; k < count; k++) {
                switch (move) {
                    case 'F' -> cmd = new MoveForwardCommand(explorer, maze);
                    case 'L' -> cmd = new TurnLeftCommand(explorer);
                    case 'R' -> cmd = new TurnRightCommand(explorer);
                    default -> logger.error("Invalid move character: {}", move);
                }
                if (cmd != null) commands.add(cmd);
            }
        }

        return commands;
    }
}