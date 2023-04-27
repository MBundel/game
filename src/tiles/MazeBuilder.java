package tiles;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Arrays;

public class MazeBuilder {
    private final int cols, rows;   // dimension of maze
    private boolean[][] north;      // is there a wall to the north of the cell?
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;

    public MazeBuilder(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        int height = 400;
        int width = (int) Math.round(1.0 * height * cols / rows);
        init();
        generate(1, 1);
    }

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[cols + 2][rows + 2];
        for (int col = 0; col < cols + 2; col++) {
            visited[col][0] = true;
            visited[col][rows + 1] = true;
        }
        for (int row = 0; row < rows + 2; row++) {
            visited[0][row] = true;
            visited[cols + 1][row] = true;
        }

        // initialize all walls as present
        north = new boolean[cols + 2][rows + 2];
        east = new boolean[cols + 2][rows + 2];
        south = new boolean[cols + 2][rows + 2];
        west = new boolean[cols + 2][rows + 2];
        for (int col = 0; col < cols + 2; col++) {
            for (int row = 0; row < rows + 2; row++) {
                north[col][row] = true;
                east[col][row] = true;
                south[col][row] = true;
                west[col][row] = true;
            }
        }
    }

    // generate the maze
    private void generate(int col, int row) {
        visited[col][row] = true;

        // while there is an unvisited neighbor
        while (!visited[col][row + 1] || !visited[col + 1][row]
                || !visited[col][row - 1] || !visited[col - 1][row]) {

            // pick random neighbor (could use Knuth's trick instead)
            while (true) {
                int r = ThreadLocalRandom.current().nextInt(0, 4);
                if (r == 0 && !visited[col][row + 1]) {
                    north[col][row] = false;
                    south[col][row + 1] = false;
                    generate(col, row + 1);
                    break;
                }
                else if (r == 1 && !visited[col + 1][row]) {
                    east[col][row] = false;
                    west[col + 1][row] = false;
                    generate(col + 1, row);
                    break;
                }
                else if (r == 2 && !visited[col][row - 1]) {
                    south[col][row] = false;
                    north[col][row - 1] = false;
                    generate(col, row - 1);
                    break;
                }
                else if (r == 3 && !visited[col - 1][row]) {
                    west[col][row] = false;
                    east[col - 1][row] = false;
                    generate(col - 1, row);
                    break;
                }
            }
        }
    }

    // main method
    public static void main(String[] args) throws FileNotFoundException {
        int cols = 32;
        int rows = 24;
        MazeBuilder maze = new MazeBuilder(cols, rows);
        String[][] map = new String[rows * 2 + 1][cols * 2 + 1];
        for (String[] row : map) {
            Arrays.fill(row, "4"); // trees
        }

        for (int i = 1; i < rows * 2; i++) {
            for (int j = 1; j < cols * 2; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    map[i][j] = "0"; // grass
                }
            }
        }

        // if there's no wall, replace tree with grass
        for (int i = 1; i < rows * 2; i++) {
            for (int j = 1; j < cols * 2; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    int orgRow = (i + 1) / 2;
                    int orgCol = (j + 1) / 2;
                    if (!maze.east[orgCol][orgRow]) {
                        map[i][j + 1] = "0";
                    }
                    if (!maze.south[orgCol][orgRow]) {
                        map[i - 1][j] = "0";
                    }
                }
            }
        }

        // write to file
        PrintWriter out = new PrintWriter("/Users/tayoneumann/IdeaProjects/batavia/res/maps/maze.txt");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                out.print(map[i][j] + " ");
            }
            out.println();
        }
        out.close();

    }
}
