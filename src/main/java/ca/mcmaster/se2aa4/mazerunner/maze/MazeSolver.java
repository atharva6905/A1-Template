package ca.mcmaster.se2aa4.mazerunner.maze;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;

public class MazeSolver {
    private Maze maze;
    private Explorer explorer;

    public MazeSolver(Maze maze, Explorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    public String solve() {
        // Implement solving algorithm here later
        return "";
    }

    public boolean verifyPath(String path) {
        for (char move : path.toCharArray()) {
            switch (move) {
                case 'F':
                    if (!explorer.moveForward(maze)) {
                        displayFinalPosition();
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
                    displayFinalPosition();
                    return false;
            }
        }
        displayFinalPosition();
        return true;
    }

    private void displayFinalPosition() {
        char[][] grid = maze.getGrid();
        Position pos = explorer.getPosition();
        grid[pos.getY()][pos.getX()] = '*';
        for (char[] row : grid) {
            System.out.println(row);
        }
    }
}