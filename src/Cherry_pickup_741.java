import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Cherry_pickup_741 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }



    class Solution {
        private int max;

        public int cherryPickup(int[][] grid) {
            int[][] picked = new int[grid.length][grid[0].length];
            // for (int i = 0; i < grid.length; i++) {
            //     for (int j = 0; j < grid[0].length; j++) {
            //         System.out.print(String.format("%02d", grid[i][j]) + "  " );
            //     }
            //     System.out.println();
            // }
            // System.out.println();

            for (int i = grid.length - 1; i >= 0; i--) {
                for (int j = grid[0].length - 1; j >= 0; j--) {
                    if (i == grid.length - 1 && j == grid[0].length - 1){
                        continue;
                    }

                    if (i == grid.length - 1){
                        if (grid[i][j + 1] == -1) {
                            grid[i][j] = -1;
                        }
                        continue;
                    }

                    if (j == grid[0].length - 1) {
                        if (grid[i + 1][j] == -1) {
                            grid[i][j] = -1;
                        }
                        continue;
                    }

                    if (grid[i + 1][j] == -1 && grid[i][j + 1] == -1) {
                        grid[i][j] = -1;
                    }
                }
            }

//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                System.out.print(String.format("%02d", grid[i][j]) + "  " );
//            }
//            System.out.println();
//        }

            recFunc(grid, picked, 0, 0, 0);
            return max;
        }

        private void recFunc(int[][] grid, int[][] picked, int row, int col, Integer sum) {
            if (row >= grid.length || col >= grid[0].length) {
                return;
            }

            int curVal = grid[row][col];
            if (curVal == -1) {
                return;
            }

            grid[row][col] = 0;
            sum += curVal;

            if (row == grid.length - 1 && col == grid[0].length - 1) {
                int total = sum + findBestWay(grid, picked);
                max = Math.max(max, total);
            }

            recFunc(grid, picked, row + 1, col, sum);
            recFunc(grid, picked, row, col + 1, sum);

            grid[row][col] = curVal;
        }

        private int findBestWay(int[][] grid, int[][] picked) {
            Queue<Square> queue = new LinkedList<>();

            int totalRows = grid.length;
            int totalCols = grid[0].length;
            int[][] added = new int[totalRows][totalCols];

            queue.offer(new Square(0, 0));
            while (!queue.isEmpty()) {
                Square square = queue.poll();
                int row = square.row;
                int col = square.col;
                int value = grid[row][col];
                if (value == -1) {
                    continue;
                }

                int prevRowPicked = (row == 0) ? 0 : picked[row - 1][col];
                int prevColPicked = (col == 0) ? 0 : picked[row][col - 1];

                picked[row][col] = value + Math.max(prevRowPicked, prevColPicked);

                if (row < totalRows - 1 && added[row + 1][col] == 0) {
                    queue.offer(new Square(row + 1, col));
                    added[row + 1][col] = 1;
                }

                if (col < totalCols - 1 && added[row][col + 1] == 0) {
                    queue.offer(new Square(row, col + 1));
                    added[row][col + 1] = 1;
                }
            }

            return Math.max(0, picked[picked.length - 1][picked[0].length - 1]);
        }

        private class Square {
            int row;
            int col;

            Square(int row, int col) {
                this.row = row;
                this.col = col;


            }

            @Override
            public String toString() {
                return "Square{" +
                        "row=" + row +
                        ", col=" + col +
                        '}';
            }
        }
    }
}

