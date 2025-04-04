package ca.mcmaster.se2aa4.mazerunner.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class ExplorerTest {

    @Test
    public void testMoveForward() {
        char[][] grid = {
            "###".toCharArray(),
            "#  ".toCharArray(),
            "###".toCharArray()
        };
        Maze maze = new Maze(grid);
        Position start = new Position(1, 1, 'E'); // Facing an open space
        Explorer explorer = new Explorer(start);

        boolean moved = explorer.moveForward(maze);
        assertTrue(moved);
        assertEquals(2, explorer.getPosition().getX());
        assertEquals(1, explorer.getPosition().getY());
    }

    @Test
    public void testTurnLeft() {
        Position pos = new Position(0, 0, 'N');
        Explorer explorer = new Explorer(pos);

        explorer.turnLeft();
        assertEquals('W', explorer.getPosition().getDirection());

        explorer.turnLeft();
        assertEquals('S', explorer.getPosition().getDirection());

        explorer.turnLeft();
        assertEquals('E', explorer.getPosition().getDirection());

        explorer.turnLeft();
        assertEquals('N', explorer.getPosition().getDirection());
    }

    @Test
    public void testTurnRight() {
        Position pos = new Position(0, 0, 'N');
        Explorer explorer = new Explorer(pos);

        explorer.turnRight();
        assertEquals('E', explorer.getPosition().getDirection());

        explorer.turnRight();
        assertEquals('S', explorer.getPosition().getDirection());

        explorer.turnRight();
        assertEquals('W', explorer.getPosition().getDirection());

        explorer.turnRight();
        assertEquals('N', explorer.getPosition().getDirection());
    }
}
