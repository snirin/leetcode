public class Cat_And_Mouse_913 {


    public static void main(String[] args) {
        //[[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
//        int[][] graph = new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        //[[2,3],[3,4],[0,4],[0,1],[1,2]]
        int[][] graph = new int[][]{{2,3},{3,4},{0,4},{0,1},{1,2}};

        System.out.println(new Solution().catMouseGame(graph));
    }

    static class Solution {
        public int catMouseGame(int[][] graph) {
            int length = graph.length;
            int pos[][][] = new int[length][length][2]; //0 dimension - mouse, 1 - cat, 3 - move turn (0-mouse, 1-cat); 1 value - mouse wins, 2 - cat

            for (int m = 0; m < length; m++) {
                for (int c = 0; c < length; c++) {
                    for (int t = 0; t <= 1; t++) {
                        if (m == 0 || c == 0) {
                            pos[m][c][t] = 1;
                        } else if (m == c) {
                            pos[m][c][t] = 2;
                        }
                    }
                }
            }
//            System.out.println(getString(pos));

            boolean flag = true;
            while (flag) {
                flag = false;

                //Mouse's move
                for (int m = 1; m < length; m++) {
                    for (int c = 1; c < length; c++) {
                        if (pos[m][c][0] == 0) {
                            boolean lost = true;
                            for (int i = 0; i < graph[m].length; i++) {
                                int newM = graph[m][i];
                                if (pos[newM][c][1] != 2) {
                                    lost = false;
                                }
                                if (pos[newM][c][1] == 1) {
                                    pos[m][c][0] = 1;
                                    flag = true;
                                    break;
                                }
                            }
                            if (lost) {
                                pos[m][c][0] = 2;
                                flag = true;
                            }
                        }
                    }
                }

                // Cat's move
                for (int m = 1; m < length; m++) {
                    for (int c = 1; c < length; c++) {
                        if (pos[m][c][1] == 0) {
                            boolean lost = true;
                            for (int i = 0; i < graph[c].length; i++) {
                                int newC = graph[c][i];
                                if (pos[m][newC][0] != 1) {
                                    lost = false;
                                }
                                if (pos[m][newC][0] == 2) {
                                    pos[m][c][1] = 2;
                                    flag = true;
                                    break;
                                }
                            }
                            if (lost) {
                                pos[m][c][1] = 1;
                                flag = true;
                            }
                        }
                    }
                }
//                System.out.println(getString(pos));
            }

            return pos[1][2][0];
        }

//        String getString(int pos[][][]) {
//            StringBuilder sb = new StringBuilder();
//            int length = pos.length;
//            for (int t = 0; t <= 1; t++) {
//                sb.append(t ==0 ? "MOUSE MOVE\n" : "CAT MOVE\n");
//                for (int m = 0; m < length; m++) {
//                    for (int c = 0; c < length; c++) {
//                        sb.append(pos[m][c][t]);
//                    }
//                    sb.append("\n");
//                }
//            }
//
//            return sb.toString();
//        }
    }
}

