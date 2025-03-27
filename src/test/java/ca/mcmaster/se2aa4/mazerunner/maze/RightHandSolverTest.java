package ca.mcmaster.se2aa4.mazerunner.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.explorer.Explorer;
import ca.mcmaster.se2aa4.mazerunner.explorer.Position;

public class RightHandSolverTest {

    @Test
    public void testRightHandSolverSimpleMaze() {
        char[][] grid = {
            "#######".toCharArray(),
            "#      ".toCharArray(),
            "### ###".toCharArray(),
            "      #".toCharArray(),
            "#######".toCharArray()
        };

        Maze maze = new Maze(grid);
        Position start = new Position(maze.getEntryX(), maze.getEntryY(), 'E');
        Explorer explorer = new Explorer(start);

        RightHandSolver solver = new RightHandSolver(maze, explorer);
        String solution = solver.solve();

        String expected = "5F2L2FR2FR3F";

        assertEquals(expected, solution);
    }
}
