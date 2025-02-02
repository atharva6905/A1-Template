package ca.mcmaster.se2aa4.mazerunner.explorer;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class Explorer {
    private Position position; // x = column, y = row (0,0 at top-left)

    public Explorer(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Moves the explorer one step in the direction it's facing.
     * - North: decrease row
     * - South: increase row
     * - East:  increase column
     * - West:  decrease column
     *
     * The explorer moves only if the target cell is a space (' ').
     */
    public boolean moveForward(Maze maze) {
        int row = position.getY();
        int col = position.getX();

        switch (position.getDirection()) {
            case 'N': row--; break;
            case 'E': col++; break;
            case 'S': row++; break;
            case 'W': col--; break;
        }
        if (!isValidMove(maze, row, col)) {
            return false;
        }
        position.setX(col);
        position.setY(row);
        return true;
    }

    private boolean isValidMove(Maze maze, int row, int col) {
        if (row < 0 || row >= maze.getGrid().length ||
            col < 0 || col >= maze.getGrid()[0].length) {
            return false;
        }
        return maze.getGrid()[row][col] == ' ';
    }

    public void turnLeft() {
        switch (position.getDirection()) {
            case 'N': position.setDirection('W'); break;
            case 'E': position.setDirection('N'); break;
            case 'S': position.setDirection('E'); break;
            case 'W': position.setDirection('S'); break;
        }
    }

    public void turnRight() {
        switch (position.getDirection()) {
            case 'N': position.setDirection('E'); break;
            case 'E': position.setDirection('S'); break;
            case 'S': position.setDirection('W'); break;
            case 'W': position.setDirection('N'); break;
        }
    }
}
