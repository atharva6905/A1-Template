package ca.mcmaster.se2aa4.mazerunner.explorer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovementLogger implements ExplorerObserver {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onMove(Position pos) {
        logger.info("Moved to: (" + pos.getY() + ", " + pos.getX() + ")");
    }

    @Override
    public void onTurn(char dir) {
        logger.info("Turned to: " + dir);
    }
}
