import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import static java.util.Arrays.asList;

public class Max_points_on_a_line_149 {

    public static void main(String[] args) {

        //3
        int[][] points1 = new int[][]{{1,1},{2,2},{3,3}};
        System.out.println(new Solution().maxPoints(points1));

        //4
        int[][] points2 = new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        System.out.println(new Solution().maxPoints(points2));

        //2
        int[][] points3 = new int[][]{{0,0},{0,0}};
        System.out.println(new Solution().maxPoints(points3));

        //3
        int[][] points4 = new int[][]{{0,0},{1,1},{0,0}};
        System.out.println(new Solution().maxPoints(points4));

//        System.out.println(new Solution().getLine(1, 1, 2, 2));
//        System.out.println(new Solution().getLine(1, 1, 1, 2));
//        System.out.println(new Solution().getLine(1, 2, 2, 2));
//
//        System.out.println(new Solution().getLine(1, -1, 2, -2));
    }

    static class Solution {
        public int maxPoints(int[][] points) {
            if (points.length == 1) {
                return 1;
            }

            Map<String, Set<Integer>> map = new HashMap<>();

            int length = points.length;
            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {

                    int x1 = points[i][0];
                    int y1 = points[i][1];
                    int x2 = points[j][0];
                    int y2 = points[j][1];
                    String line = getLine(x1, y1, x2, y2);

                    if (!map.containsKey(line)) {
                        map.put(line, new HashSet<>());
                    }
                    map.get(line).addAll(Arrays.asList(i, j));
                }
            }

            int max = 0;
            for (Set<Integer> set : map.values()) {
                max = Math.max(max, set.size());
            }
            return max;
        }

        private String getLine(int x1, int y1, int x2, int y2) {
            if (x1 == x2) {
                return "x=" + x1;
            } else if (y1 == y2) {
                return "y=" + y1;
            }
            int dx = x2 - x1;
            int dy = y2 - y1;
            int gtd = findGCD(dx, dy);
            dx = dx / gtd;
            dy = dy / gtd;
            int sign = dx < 0 ? -1 : 1;

            int coef = (x1 / dx);
            int startX = x1 - coef * dx;
            int startY = y1 - coef * dy;
            int endX = startX + sign * dx;
            int endY = startY + sign * dy;
            return  "" + startX + "/" + startY + "/" + endX + "/" + endY;
        }

        int findGCD(int a, int b) {
            int min = Math.min(Math.abs(a), Math.abs(b));
            int max = Math.max(Math.abs(a), Math.abs(b));

            int rest = 1;
            while (rest > 0) {
                rest = max % min;
                max = min;
                min = rest;
            }

            return max;
        }
    }
}

