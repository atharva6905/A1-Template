package ca.mcmaster.se2aa4.mazerunner.maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        int i = 0;
        int stepCounter = 0;
        while (i < path.length()) {
            int count = 0;
            // Parse count prefix.
            while (i < path.length() && Character.isDigit(path.charAt(i))) {
                count = count * 10 + (path.charAt(i) - '0');
                i++;
            }
            if (count == 0) {
                count = 1;
            }
            if (i >= path.length()) {
                logger.error("Path ended unexpectedly after a number.");
                return false;
            }
            char move = path.charAt(i++);
            // Execute the command 'count' times.
            for (int k = 0; k < count; k++) {
                stepCounter++;
                if (move == 'F') {
                    if (!explorer.moveForward(maze)) {
                        logger.error("Path verification failed at step {} with move '{}'", stepCounter, move);
                        return false;
                    }
                } else if (move == 'L') {
                    explorer.turnLeft();
                } else if (move == 'R') {
                    explorer.turnRight();
                } else {
                    logger.error("Invalid move character detected: '{}'", move);
                    return false;
                }
            }
        }
        // Check if explorer is at the end position.
        if (explorer.getPosition().getX() == endX && explorer.getPosition().getY() == endY) {
            return true;
        } else {
            logger.error("Path complete, but explorer is at (row={}, col={}) instead of end (row={}, col={})",
                explorer.getPosition().getY(), explorer.getPosition().getX(),
                endY, endX);
            return false;
        }
    }
}
