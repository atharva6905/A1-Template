package ca.mcmaster.se2aa4.mazerunner.maze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MazeLoader {
    public static Maze loadMaze(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<char[]> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.matches("[# ]+")) {
                throw new IOException("Invalid maze format: " + line);
            }
            lines.add(line.toCharArray());
        }
        reader.close();
        if (lines.isEmpty()) {
            throw new IOException("Maze file is empty");
        }
        char[][] grid = lines.toArray(new char[lines.size()][]);
        return new Maze(grid);
    }
}