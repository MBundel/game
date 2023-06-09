package tiles;

import java.util.concurrent.ThreadLocalRandom;

public class Maze {

    // fields
    public final int cols, rows;   // dimension of maze
    public boolean[][] north;      // true if there's a wall to the north of the cell
    public boolean[][] east;
    public boolean[][] south;
    public boolean[][] west;
    private boolean[][] visited;
    private boolean flag;

    // constructor
    public Maze(int cols, int rows, boolean flag) {
        this.cols = cols;
        this.rows = rows;
        this.flag = flag;
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
        if (flag) {
            for (int y = 5; y < 9; y++) {
                for (int x = 5; x < 9; x++) {
                    visited[y][x] = true;
                }
            }
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

            // pick random neighbor
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
}
