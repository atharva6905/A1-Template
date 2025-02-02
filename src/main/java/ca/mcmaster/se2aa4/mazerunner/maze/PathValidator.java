package ca.mcmaster.se2aa4.mazerunner.maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;

public class PathValidator {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private Explorer explorer;

    public PathValidator(Maze maze, Explorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    /**
     * Processes the input path where each command is in the form:
     *   [<count>]<command>
     * For example:
     *   "F" means one forward move,
     *   "2L" means turn left twice.
     *
     * A step counter is maintained (each executed move counts as a step).
     * If a forward move fails or an invalid command is encountered, an error is logged.
     * At the end, the explorer must be at the maze exit for the path to be valid.
     */
    public boolean verifyPath(String path) {
        int i = 0;
        int stepCounter = 0;
        while (i < path.length()) {
            int count = 0;
            // Parse optional count prefix.
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
                switch (move) {
                    case 'F':
                        if (!explorer.moveForward(maze)) {
                            logger.error("Path verification failed at step {} with move '{}'", stepCounter, move);
                            return false;
                        }
                        break;
                    case 'L':
                        explorer.turnLeft();
                        break;
                    case 'R':
                        explorer.turnRight();
                        break;
                    default:
                        logger.error("Invalid move character detected: '{}'", move);
                        return false;
                }
            }
        }
        // Check if explorer is at the exit.
        if (explorer.getPosition().getX() == maze.getExitX() &&
            explorer.getPosition().getY() == maze.getExitY()) {
            return true;
        } else {
            logger.error("Path complete, but explorer is at (row={}, col={}) instead of exit (row={}, col={})",
                explorer.getPosition().getY(), explorer.getPosition().getX(),
                maze.getExitY(), maze.getExitX());
            return false;
        }
    }
}
