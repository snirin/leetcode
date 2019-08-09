import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gas_Station_134 {

    public static void main(String[] args) {
        check(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}, 3);
        check(new int[]{2,3,4}, new int[]{3,4,3}, -1);
    }

    static void check(int[] gas, int[] cost, int expected) {
        int result = new Solution().canCompleteCircuit(gas, cost);

        if (expected != result) {
            System.out.println("" + gas + "        " + cost + "    " + expected + "          " + result);
        }
    }

    static class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int length = gas.length;

            int res = 0;
            int start = 0;
            int i = 0;
            while (true) {
                res = res + gas[i] - cost[i];

                i = (i + 1) % length;
                if (res < 0) {
                    if (start >= i) {
                        return -1;
                    }
                    start = i;
                    res = 0;
                } else {
                    if (i == start) {
                        return start;
                    }
                }

            }
        }
    }
}

