package ca.mcmaster.se2aa4.mazerunner.command;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class MoveForwardCommand implements Command {
    private final Explorer explorer;
    private final Maze maze;

    public MoveForwardCommand(Explorer explorer, Maze maze) {
        this.explorer = explorer;
        this.maze = maze;
    }

    @Override
    public boolean execute() {
        return explorer.moveForward(maze);
    }
}
