public class Decode_Ways_91 {

    public static void main(String[] args) {
        check("12", 2);
        check("226", 3);
    }

    static void check(String s, int expected) {
        int result = new Solution().numDecodings(s);

        if (expected != result) {
            System.out.println("" + s + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public int numDecodings(String s) {

            int prev = 1;
            int prevPrev = 1;
            int res = 0;
            char prevC = '0';
            for (char c : s.toCharArray()) {
                res = 0;

                if (c != '0') {
                    res += prev;
                }

                if (prevC != '0' && 10*(prevC - '0')+(c-'0') <= 26) {
                    res += prevPrev;
                }

                prevPrev = prev;
                prev = res;
                prevC = c;
            }

            return res;
        }
    }
}

