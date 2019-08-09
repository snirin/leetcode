import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Sudoku_Solver_37 {

    public static void main(String[] args) {
        char[][] sud1 = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8',
                '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7',
                '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.',
                '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] res1 = new char[][]{{'5', '3', '4', '6', '7', '8', '9', '1', '2'}, {'6', '7', '2', '1', '9', '5', '3', '4', '8'}, {'1', '9', '8',
                '3', '4', '2', '5', '6', '7'}, {'8', '5', '9', '7', '6', '1', '4', '2', '3'}, {'4', '2', '6', '8', '5', '3', '7', '9', '1'}, {'7',
                '1', '3', '9', '2', '4', '8', '5', '6'}, {'9', '6', '1', '5', '3', '7', '2', '8', '4'}, {'2', '8', '7', '4', '1', '9', '6', '3',
                '5'}, {'3', '4', '5', '2', '8', '6', '1', '7', '9'}};

        char[][] sud2 = new char[][]{{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}};

        char[][] sud3 = new char[][]{{'.','.','.','2','.','.','.','6','3'},{'3','.','.','.','.','5','4','.','1'},{'.','.','1','.','.','3','9','8','.'},{'.','.','.','.','.','.','.','9','.'},{'.','.','.','5','3','8','.','.','.'},{'.','3','.','.','.','.','.','.','.'},{'.','2','6','3','.','.','5','.','.'},{'5','.','3','7','.','.','.','.','8'},{'4','7','.','.','.','1','.','.','.'}};

        char[][] sud4 = new char[][]{{'1','.','.','.','7','.','.','3','.'},{'8','3','.','6','.','.','.','.','.'},{'.','.','2','9','.','.','6','.','8'},{'6','.','.','.','.','4','9','.','7'},{'.','9','.','.','.','.','.','5','.'},{'3','.','7','5','.','.','.','.','4'},{'2','.','3','.','.','9','1','.','.'},{'.','.','.','.','.','2','.','4','3'},{'.','4','.','.','8','.','.','.','9'}};

//        check(sud1, res1);
//        check(sud2, res1);
        check(sud4, res1);
    }

    static void check(char[][] board, char[][] expected) {
        new Solution().solveSudoku(board);
        char[][] result = board;

        if (!Objects.equals(expected, result)) {
            System.out.println("" + board + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public void solveSudoku(char[][] board) {
            Set[][] possibleValues = new Set[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.') {
                        possibleValues[i][j] = new HashSet(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));
                    } else {
                        possibleValues[i][j] = new HashSet(Collections.singleton(board[i][j]));
                    }
                }
            }

            possibleValues = recurse(possibleValues);

//            printBoard(possibleValues);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = (char)possibleValues[i][j].toArray()[0];
                }
            }
        }

        private void printBoard(Set[][] possibleValues) {
            System.out.println();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(possibleValues[i][j]);
                }
                System.out.println();
            }
        }

        private Set[][] recurse(Set[][] possibleValues) {
            if (!clean(possibleValues)) {
                return null;
            }

//            printBoard(possibleValues);

            boolean flag = true;
            int minSize = 9;
            int row = 0, col = 0;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    Set values = possibleValues[i][j];
                    if (values.size() == 0) {
                        return null;
                    }
                    if (values.size() > 1) {
                        flag = false;
                        if (values.size() < minSize) {
                            minSize = values.size();
                            row = i;
                            col = j;
                        }
//                        Set[][] possibleValues1 = new Set[9][9];
//
//                        for (Object value : values) {
//                            for (int i1 = 0; i1 < 9; i1++) {
//                                for (int j1 = 0; j1 < 9; j1++) {
//                                    possibleValues1[i1][j1] = new HashSet(possibleValues[i1][j1]);
//                                }
//                            }
//
//                            possibleValues1[i][j] = Collections.singleton(value);
//                            Set[][] recurse = recurse(possibleValues1);
//                            if (recurse != null) {
//                                return recurse;
//                            }
//                        }
                    }
                }
            }

            if (flag) {
                return possibleValues;
            }

            Set values = possibleValues[row][col];
            Set[][] possibleValues1 = new Set[9][9];
            for (Object value : values) {
                for (int i1 = 0; i1 < 9; i1++) {
                    for (int j1 = 0; j1 < 9; j1++) {
                        possibleValues1[i1][j1] = new HashSet(possibleValues[i1][j1]);
                    }
                }

                possibleValues1[row][col] = Collections.singleton(value);
                Set[][] recurse = recurse(possibleValues1);
                if (recurse != null) {
                    return recurse;
                }
            }

            return null;
        }

        private boolean clean(Set[][] possibleValues) {
            boolean found = true;
            while (found) {
                found = false;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        Set values = possibleValues[i][j];
                        if (values.size() == 0) {
                            return false;
                        } else if (values.size() == 1) {
                            char value = (char) values.toArray()[0];
                            for (int i1 = 0; i1 < 9; i1++) {
                                if (i1 != i) {
                                    Set values1 = possibleValues[i1][j];
                                    if (values1.contains(value)) {
                                        values1.remove(value);
                                        found = true;
                                    }
                                }
                            }
                            for (int j1 = 0; j1 < 9; j1++) {
                                if (j1 != j) {
                                    Set values1 = possibleValues[i][j1];
                                    if (values1.contains(value)) {
                                        values1.remove(value);
                                        found = true;
                                    }
                                }
                            }

                            int iBeginSquare = (i / 3) * 3;
                            int jBeginSquare = (j / 3) * 3;
                            for (int i1 = iBeginSquare; i1 <= iBeginSquare + 2; i1++) {
                                for (int j1 = jBeginSquare; j1 <= jBeginSquare + 2; j1++) {
                                    if (i1 != i || j1 != j) {
                                        Set values1 = possibleValues[i1][j1];
                                        if (values1.contains(value)) {
                                            values1.remove(value);
                                            found = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}

