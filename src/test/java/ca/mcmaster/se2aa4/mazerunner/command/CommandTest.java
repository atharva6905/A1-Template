package ca.mcmaster.se2aa4.mazerunner.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;

public class CommandTest {

    @Test
    public void testMoveForwardCommand() {
        char[][] grid = {
            "###".toCharArray(),
            "#  ".toCharArray(),
            "###".toCharArray()
        };
        Maze maze = new Maze(grid);
        Explorer explorer = new Explorer(new Position(1, 1, 'E')); // Facing right
        Command move = new MoveForwardCommand(explorer, maze);

        boolean moved = move.execute();

        assertTrue(moved);
        assertEquals(2, explorer.getPosition().getX());
        assertEquals(1, explorer.getPosition().getY());
    }

    @Test
    public void testMoveForwardBlocked() {
        char[][] grid = {
            "###".toCharArray(),
            "# #".toCharArray(),
            "###".toCharArray()
        };
        Maze maze = new Maze(grid);
        Explorer explorer = new Explorer(new Position(1, 1, 'E'));
        Command move = new MoveForwardCommand(explorer, maze);

        boolean moved = move.execute();

        assertFalse(moved);
        assertEquals(1, explorer.getPosition().getX());
        assertEquals(1, explorer.getPosition().getY());
    }

    @Test
    public void testTurnLeftCommand() {
        Explorer explorer = new Explorer(new Position(1, 1, 'N'));
        Command turnLeft = new TurnLeftCommand(explorer);
        turnLeft.execute();
        assertEquals('W', explorer.getPosition().getDirection());
    }

    @Test
    public void testTurnRightCommand() {
        Explorer explorer = new Explorer(new Position(1, 1, 'N'));
        Command turnRight = new TurnRightCommand(explorer);
        turnRight.execute();
        assertEquals('E', explorer.getPosition().getDirection());
    }
}
