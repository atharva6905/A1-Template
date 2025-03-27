package ca.mcmaster.se2aa4.mazerunner.command;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;

public class TurnRightCommand implements Command {
    private final Explorer explorer;

    public TurnRightCommand(Explorer explorer) {
        this.explorer = explorer;
    }

    @Override
    public boolean execute() {
        explorer.turnRight();
        return true;
    }
}
