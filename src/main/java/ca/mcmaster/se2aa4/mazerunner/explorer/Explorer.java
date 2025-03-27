package ca.mcmaster.se2aa4.mazerunner.explorer;

import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class Explorer {
    private Position position; 
    private List<ExplorerObserver> observers = new ArrayList<>();

    public Explorer(Position position) {
        this.position = position;
        
    }

    public void addObserver(ExplorerObserver observer) {
        observers.add(observer);
    }

    private void notifyMove() {
        for (ExplorerObserver obs : observers) {
            obs.onMove(position);
        }
    }

    private void notifyTurn() {
        for (ExplorerObserver obs : observers) {
            obs.onTurn(position.getDirection());
        }
    }

    public Position getPosition() {
        return position;
    }

    public boolean moveForward(Maze maze) {
        int row = position.getY();
        int col = position.getX();
        char direction = position.getDirection();

        switch (direction) {
            case 'N':
                row--;
                break;
            case 'E':
                col++;
                break;
            case 'S':
                row++;
                break;
            case 'W':
                col--;
                break;
            default:
                break;
        }

        if (!isValidMove(maze, row, col)) {
            return false;
        }
        position.setX(col);
        position.setY(row);
        notifyMove();
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
        switch (direction) {
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
            default:
                break;
        }
        notifyTurn();
    }


    public void turnRight() {
        char direction = position.getDirection();
        switch (direction) {
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
            default:
                break;
        }
        notifyTurn();
    }
}
