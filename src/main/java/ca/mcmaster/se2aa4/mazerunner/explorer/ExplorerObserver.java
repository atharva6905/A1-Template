package ca.mcmaster.se2aa4.mazerunner.explorer;

public interface ExplorerObserver {
    void onMove(Position newPosition);
    void onTurn(char newDirection);
}
