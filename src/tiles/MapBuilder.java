package tiles;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MapBuilder {

    int cols, rows;
    public int key1x, key1y, key2x, key2y, key3x, keyx3y, key4x, key4y, chestx, chesty, bootsx, bootsy;
    public int[][] tileNum;

    public MapBuilder() {

        // a maze with 12 corridors (rows or columns) has 13 walls (12 + 13 = 25)
        // both corridors and walls are doubled in width to facilitate movement (less collision)
        // so a 12x12 maze returns a 50x50 tile map
        this.cols = 12;
        this.rows = 12;
        Maze maze1 = new Maze(cols, rows, false);
        Maze maze2 = new Maze(cols, rows, false);
        Maze maze3 = new Maze(cols, rows, true);

        int[][] concreteMaze1 = mazeBuilder(maze1, "grass", "tree");
        int[][] concreteMaze2 = mazeBuilder(maze2, "road", "water");
        int[][] concreteMaze3 = mazeBuilder(maze3, "earth", "wall");

        this.tileNum = mapBuilder(concreteMaze1, concreteMaze2, concreteMaze3);

    }

    public int[][] mazeBuilder(Maze abstractMaze, String corridor, String wall) {

        Map<String, Integer> tile2num = new HashMap<>();
        tile2num.put("grass", 0);
        tile2num.put("wall", 1);
        tile2num.put("water", 2);
        tile2num.put("earth", 3);
        tile2num.put("tree", 4);
        tile2num.put("road", 5);

        int[][] concreteMaze = new int[abstractMaze.rows * 2 + 1][abstractMaze.cols * 2 + 1];
        for (int[] row : concreteMaze) {
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

    public int[][] mapBuilder(int[][] maze1, int[][] maze2, int[][] maze3) {

        int[][] map = new int[100][100];

        // first quadrant on map (upper left)
        // 1 2
        // 3 4
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                map[i*2][j*2] = maze3[i][j];
                map[i*2][j*2+1] = maze3[i][j];
                map[i*2+1][j*2] = maze3[i][j];
                map[i*2+1][j*2+1] = maze3[i][j];
            }
        }
        // prison for the princess
        for (int i = 18; i < 32; i++) {
            for (int j = 18; j < 32; j++) {
                map[i][j] = 0;
            }
        }

        // second quadrant on map (upper right)
        // 1 2
        // 3 4
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                map[i*2][j*2+50] = maze2[i][j];
                map[i*2][j*2+1+50] = maze2[i][j];
                map[i*2+1][j*2+50] = maze2[i][j];
                map[i*2+1][j*2+1+50] = maze2[i][j];
            }
        }

        // fourth quadrant on map (lower right)
        // 1 2
        // 3 4
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                map[i*2+50][j*2+50] = maze1[i][j];
                map[i*2+50][j*2+1+50] = maze1[i][j];
                map[i*2+1+50][j*2+50] = maze1[i][j];
                map[i*2+1+50][j*2+1+50] = maze1[i][j];
            }
        }

        // third quadrant on map (lower left)
        // 1 2
        // 3 4
        for (int i = 50; i < 100; i++) {
            for (int j = 0; j < 50; j++) {
                map[i][j] = 0;
            }
        }

        // add entrance to walled-off area
        map[48][23] = 3;
        map[49][23] = 3;
        // add entrance to prison
        map[16][23] = 3;
        map[17][23] = 3;

        // add entrance to forest
        map[72][50] = 0;
        map[73][50] = 0;
        map[72][51] = 0;
        map[73][51] = 0;

        // add entrance to river area
        // upper right corner of the forest
        map[50][96] = 0;
        map[51][96] = 0;
        map[50][97] = 0;
        map[51][97] = 0;
        // lower right corner of the river area
        map[48][96] = 5;
        map[49][96] = 5;
        map[48][97] = 5;
        map[49][97] = 5;

        // add road to forest
        for (int y = 51; y < 73; y++) {
            map[y][22] = 8;
            map[y][23] = 5;
            map[y][24] = 9;
            if (y % 2 == 0) map[y][21] = 4;
        }
        for (int x = 24; x < 49; x++) {
            map[71][x] = 6;
            map[72][x] = 5;
            map[73][x] = 7;
            if (x % 2 == 0) map[74][x] = 4;
        }
        // draw corner of the road
        map[71][24] = 11;
        map[73][22] = 10;
        map[73][23] = 7;
        map[74][21] = 4;

        // hide chest
        int[] chestyx = findHidingPlace(map, 0, 50, 0, 50, 3);
        this.chesty = chestyx[0];
        this.chestx = chestyx[1];

        // hide boots
        int[] bootsyx = findHidingPlace(map, 50, 100, 0, 50, 0);
        this.bootsy = bootsyx[0];
        this.bootsx = bootsyx[1];

        // save map
        try {
            writeToFile(map);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    public int[] findHidingPlace(int[][] map, int rowLeftBoundary, int rowRightBoundary, int colLeftBoundary, int colRightBoundary, int corridor) {
        int y;
        int x;
        while (true) {
            int randomY = ThreadLocalRandom.current().nextInt(rowLeftBoundary, rowRightBoundary);
            int randomX = ThreadLocalRandom.current().nextInt(colLeftBoundary, colRightBoundary);
            if (map[randomY][randomX] == corridor) {
                y = randomY;
                x = randomX;
                break;
            }
        }
        return new int[]{y, x}; // note that the map is ordered int[rows][cols], so it's y before x!
    }

    public void writeToFile(int[][] map) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/tayoneumann/IdeaProjects/bataviaCollaboration/res/maps/maze.txt");
        for (int[] row : map) {
            for (int tile : row) {
                out.print(tile + " ");
            }
            out.println();
        }
        out.close();
    }
}
