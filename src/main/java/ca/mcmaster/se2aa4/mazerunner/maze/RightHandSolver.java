package ca.mcmaster.se2aa4.mazerunner.maze;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;

public class RightHandSolver implements MazeSolver {
    private Maze maze;
    private Explorer explorer;

    public RightHandSolver(Maze maze, Explorer explorer) {
        this.maze = maze;
        this.explorer = explorer;
    }

    @Override
    public String solve() {
        StringBuilder path = new StringBuilder();
        while (!isAtExit()) {
            if (canMoveRight()) {
                explorer.turnRight();
                explorer.moveForward(maze);
                path.append("RF");
            } else if (canMoveForward()) {
                explorer.moveForward(maze);
                path.append('F');
            } else {
                explorer.turnLeft();
                path.append('L');
            }
        }
        String canonicalPath = path.toString();
        String factorizedPath = factorizePath(canonicalPath);
        System.out.println(factorizedPath);
        return factorizedPath;
    }

    private boolean isAtExit() {
        return explorer.getPosition().getX() == maze.getExitX() && explorer.getPosition().getY() == maze.getExitY();
    }

    private boolean canMoveRight() {
        explorer.turnRight();
        boolean canMove = canMoveForward();
        explorer.turnLeft();
        return canMove;
    }

    private boolean canMoveForward() {
        int x = explorer.getPosition().getX();
        int y = explorer.getPosition().getY();
        switch (explorer.getPosition().getDirection()) {
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
        return x >= 0 && x < maze.getGrid()[0].length && y >= 0 && y < maze.getGrid().length && maze.getGrid()[y][x] != '#';
    }

    private String factorizePath(String path) {
        StringBuilder factorized = new StringBuilder();
        int count = 1;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == path.charAt(i - 1)) {
                count++;
            } else {
                if (count > 1) {
                    factorized.append(count);
                }
                factorized.append(path.charAt(i - 1));
                count = 1;
            }
        }
        if (count > 1) {
            factorized.append(count);
        }
        factorized.append(path.charAt(path.length() - 1));
        return factorized.toString();
    }
}