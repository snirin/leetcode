public class Divide_Two_Integers_29 {

    public static void main(String[] args) {
        checkNum(0, 1, 0);
        checkNum(10, 3, 3);
        checkNum(7, -3, -2);
        checkNum(10_000, 100, 100);
        checkNum(-2147483648, -3, 715827882);
        checkNum(Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
        checkNum(Integer.MIN_VALUE, -1, Integer.MAX_VALUE);
        checkNum(Integer.MIN_VALUE, Integer.MIN_VALUE, 1);
    }

    static void checkNum(int dividend, int divisor, int expected) {
        int result = new Solution().divide(dividend, divisor);

        if (expected != result) {
            System.out.println("" + dividend + " / " + divisor + "        " + expected + "          " + result );
        }
    }

    static class Solution {

        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            if (divisor == Integer.MIN_VALUE) {
                return dividend == Integer.MIN_VALUE ? 1 : 0;
            }

            int[] pow2 = new int[30];

            int val = 1;
            for (int i = 0; i < 30; i++) {
                pow2[i] = val;
                val <<= 1;
            }

            boolean signPos = true;
            signPos = dividend < 0 ? !signPos : signPos;
            signPos = divisor < 0 ? !signPos : signPos;

            dividend = dividend < 0 ? dividend : -dividend;
            divisor = divisor < 0 ? divisor : -divisor;

            int division = 0;
            int diff = dividend;
            while (true) {
                int curRes = 0;
                int pow = 0;
                for (int i = 0; i < 30; i++) {
                    int res = multiply(divisor, pow2[i]);

                    if (!sameSign(res, divisor) || res < diff) {
                        break;
                    }

                    pow = pow2[i];
                    curRes = res;
                }

                diff -= curRes;
                division += pow;

                if (diff > divisor) {
                    break;
                }
            }

            return signPos ? division : -division;
        }

        private boolean sameSign(int x, int y) {
            boolean res = true;
            res = x < 0 ? !res : res;
            res = y < 0 ? !res : res;
            return res;
        }


        public int multiply(int x, int y) {

            boolean signPos = true;
            signPos = x < 0 ? !signPos : signPos;
            signPos = y < 0 ? !signPos : signPos;

            x = x < 0 ? -x : x;
            y = y < 0 ? -y : y;

            int min = x < y ? x : y;
            int max = x > y ? x : y;

            int res = 0;
            for (int i = 0; i < min; i++) {
                res += max;
            }

            return signPos ? res : -res;
        }
    }
}

