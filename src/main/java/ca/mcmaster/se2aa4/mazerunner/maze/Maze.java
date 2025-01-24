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
        locateEntryAndExit();
        logEntryAndExitPoints();
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

    private void locateEntryAndExit() {
        // Locate entry and exit points on the borders
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == ' ' && (x == 0 || x == grid[y].length - 1 || y == 0 || y == grid.length - 1)) {
                    if (entryX == -1 && entryY == -1) {
                        entryX = x;
                        entryY = y;
                    } else {
                        exitX = x;
                        exitY = y;
                        return;
                    }
                }
            }
        }
    }

    private void logEntryAndExitPoints() {
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
    }
}