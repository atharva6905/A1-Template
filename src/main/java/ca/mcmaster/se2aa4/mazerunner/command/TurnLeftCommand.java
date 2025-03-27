package ca.mcmaster.se2aa4.mazerunner.command;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;

public class TurnLeftCommand implements Command {
    private final Explorer explorer;

    public TurnLeftCommand(Explorer explorer) {
        this.explorer = explorer;
    }

    @Override
    public boolean execute() {
        explorer.turnLeft();
        return true;
    }
}
