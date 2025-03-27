package ca.mcmaster.se2aa4.mazerunner.maze;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;

public class PathValidatorTest {

    @Test
    public void testValidPath() {
        // 1x3 open maze: entry at (0,0), exit at (2,0)
        char[][] grid = { "   ".toCharArray() };
        Maze maze = new Maze(grid);
        Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
        PathValidator validator = new PathValidator(maze, explorer);

        // "FF" moves from (0,0) to (2,0)
        assertTrue(validator.verifyPath("FF"));
    }

    @Test
    public void testInvalidPath() {
        char[][] grid = { "   ".toCharArray() };
        Maze maze = new Maze(grid);
        Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
        PathValidator validator = new PathValidator(maze, explorer);

        // "F" moves from (0,0) to (1,0), not the exit
        assertFalse(validator.verifyPath("F"));
    }

    @Test
    public void testInvalidMoveCharacter() {
        char[][] grid = { "   ".toCharArray() };
        Maze maze = new Maze(grid);
        Explorer explorer = new Explorer(new Position(maze.getEntryX(), maze.getEntryY(), 'E'));
        PathValidator validator = new PathValidator(maze, explorer);

        // "FX" => 'X' is invalid
        assertFalse(validator.verifyPath("FX"));
    }
}
