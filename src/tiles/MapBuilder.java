package tiles;

import java.io.FileNotFoundException;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapBuilder {

    public static void main(String[] args) throws FileNotFoundException {

        // a maze with 12 corridors (rows or columns) has 13 walls (12 + 13 = 25)
        // both corridors and walls are doubled in width to facilitate movement (less collision)
        // so a 12x12 maze returns a 50x50 tile map
        int cols = 12;
        int rows = 12;
        Maze maze1 = new Maze(cols, rows);
        Maze maze2 = new Maze(cols, rows);
        Maze maze3 = new Maze(cols, rows);

        String[][] concreteMaze1 = mazeBuilder(maze1, "grass", "trees");
        String[][] concreteMaze2 = mazeBuilder(maze2, "road", "water");
        String[][] concreteMaze3 = mazeBuilder(maze3, "earth", "wall");

    }

    public static String[][] mazeBuilder(Maze abstractMaze, String corridor, String wall) {

        Map<String, String> tile2num = new HashMap<>();
        tile2num.put("grass", "0");
        tile2num.put("wall", "1");
        tile2num.put("water", "2");
        tile2num.put("earth", "3");
        tile2num.put("tree", "4");
        tile2num.put("road", "5");

        String[][] concreteMaze = new String[abstractMaze.rows * 2 + 1][abstractMaze.cols * 2 + 1];
        for (String[] row : concreteMaze) {
            Arrays.fill(row, tile2num.get(wall)); // wall, tree, or water
        }

        for (int i = 1; i < abstractMaze.rows * 2; i++) {
            for (int j = 1; j < abstractMaze.cols * 2; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    concreteMaze[i][j] = tile2num.get(corridor); // grass, earth, or road
                }
            }
        }

        // if there's no wall in the maze, replace with corridor in the array
        for (int i = 1; i < abstractMaze.rows * 2; i++) {
            for (int j = 1; j < abstractMaze.cols * 2; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    int orgRow = (i + 1) / 2;
                    int orgCol = (j + 1) / 2;
                    if (!abstractMaze.east[orgCol][orgRow]) {
                        concreteMaze[i][j + 1] = tile2num.get(corridor);
                    }
                    if (!abstractMaze.south[orgCol][orgRow]) {
                        concreteMaze[i - 1][j] = tile2num.get(corridor);
                    }
                }
            }
        }
        return concreteMaze;
    }

    public static void mapBuilder(String[][] maze1, String[][] maze2, String[] maze3) {
        PrintWriter map = new PrintWriter("maps/maze.txt");

    }
}
