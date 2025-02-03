package ca.mcmaster.se2aa4.mazerunner.explorer;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class Explorer {
    private Position position; 

    public Explorer(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean moveForward(Maze maze) {
        int row = position.getY();
        int col = position.getX();
        char direction = position.getDirection();

        if (direction == 'N') {
            row--;
        } else if (direction == 'E') {
            col++;
        } else if (direction == 'S') {
            row++;
        } else if (direction == 'W') {
            col--;
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
        char direction = position.getDirection();
        if (direction == 'N') {
            position.setDirection('W');
        } else if (direction == 'E') {
            position.setDirection('N');
        } else if (direction == 'S') {
            position.setDirection('E');
        } else if (direction == 'W') {
            position.setDirection('S');
        }
    }


    public void turnRight() {
        char direction = position.getDirection();
        if (direction == 'N') {
            position.setDirection('E');
        } else if (direction == 'E') {
            position.setDirection('S');
        } else if (direction == 'S') {
            position.setDirection('W');
        } else if (direction == 'W') {
            position.setDirection('N');
        }
    }
}
