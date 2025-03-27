package ca.mcmaster.se2aa4.mazerunner.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MazeTest {

    @Test
    public void testLocateEntryExit() {
        char[][] grid = new char[3][];
        grid[0] = " ####".toCharArray();
        grid[1] = "#####".toCharArray();
        grid[2] = "#### ".toCharArray();
        
        Maze maze = new Maze(grid);
        
        assertEquals(0, maze.getEntryX());
        assertEquals(0, maze.getEntryY());
        assertEquals(4, maze.getExitX());
        assertEquals(2, maze.getExitY());
    }

    @Test
    public void testNoEntryFound() {
        char[][] grid = {
            "####".toCharArray(),
            "####".toCharArray()
        };
        Maze maze = new Maze(grid);
        
        assertEquals(-1, maze.getEntryX());
        assertEquals(-1, maze.getEntryY());
    }
    
    @Test
    public void testNoExitFound() {
        char[][] grid = {
            "####".toCharArray(),
            "####".toCharArray()
        };
        Maze maze = new Maze(grid);
        
        assertEquals(-1, maze.getExitX());
        assertEquals(-1, maze.getExitY());
    }
}
