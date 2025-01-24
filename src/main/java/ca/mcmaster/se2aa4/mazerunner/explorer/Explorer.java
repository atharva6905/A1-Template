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
        int x = position.getX();
        int y = position.getY();
        switch (position.getDirection()) {
            case 'N':
                y--;
                break;
            case 'E':
                x++;
                break;
            case 'S':
                y++;
                break;
            case 'W':
                x--;
                break;
        }
        if (x < 0 || x >= maze.getGrid()[0].length || y < 0 || y >= maze.getGrid().length || maze.getGrid()[y][x] == '#') {
            return false;
        }
        position.setX(x);
        position.setY(y);
        return true;
    }

    public void turnLeft() {
        switch (position.getDirection()) {
            case 'N':
                position.setDirection('W');
                break;
            case 'E':
                position.setDirection('N');
                break;
            case 'S':
                position.setDirection('E');
                break;
            case 'W':
                position.setDirection('S');
                break;
        }
    }

    public void turnRight() {
        switch (position.getDirection()) {
            case 'N':
                position.setDirection('E');
                break;
            case 'E':
                position.setDirection('S');
                break;
            case 'S':
                position.setDirection('W');
                break;
            case 'W':
                position.setDirection('N');
                break;
        }
    }
}