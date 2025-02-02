package ca.mcmaster.se2aa4.mazerunner.maze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private char[][] grid;
    private int entryX = -1, entryY = -1;
    private int exitX = -1, exitY = -1;

    public Maze(char[][] grid) {
        this.grid = grid;
        locateEntryExit();
    }

    public char[][] getGrid() {
        return grid;
    }

    public int getEntryX() {
        return entryX;
    }

    public int getEntryY() {
        return entryY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    private void locateEntryExit() {
        // Locate entry point on the left wall
        for (int y = 0; y < grid.length; y++) {
            if (grid[y][0] == ' ') {
                entryX = 0;
                entryY = y;
                break;
            }
        }

        // Locate exit point on the right wall
        for (int y = 0; y < grid.length; y++) {
            if (grid[y][grid[y].length - 1] == ' ') {
                exitX = grid[y].length - 1;
                exitY = y;
                break;
            }
        }

        // Log entry and exit points
        if (entryX != -1 && entryY != -1) {
            logger.info("Entry point (row, column): (" + entryY + ", " + entryX + ")");
        } else {
            logger.error("Entry point not found in the maze.");
        }

        if (exitX != -1 && exitY != -1) {
            logger.info("Exit point (row, column): (" + exitY + ", " + exitX + ")");
        } else {
            logger.error("Exit point not found in the maze.");
        }

        if (entryX == -1 || exitX == -1) {
            logger.error("Failed to locate entry or exit points.");
        }
    }
}