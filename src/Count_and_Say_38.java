import java.util.Objects;

public class Count_and_Say_38 {

    public static void main(String[] args) {
//        check(1, "1");
//        check(2, "11");
//        check(3, "21");
        check(4, "1211");
    }

    static void check(int n, String expected) {
        String result = new Solution().countAndSay(n);

        if (!Objects.equals(expected, result)) {
            System.out.println("" + n + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public String countAndSay(int n) {
            String res = "1";

            for (int k = 2; k <= n; k++) {
                char prev = res.charAt(0);
                int count = 0;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < res.length(); i++) {
                    char c = res.charAt(i);
                    if (c == prev) {
                        count++;
                    } else {
                        sb.append(count).append(prev);
                        count = 1;
                    }
                    prev = c;
                }
                sb.append(count).append(prev);
                res = sb.toString();
            }

            return res;
        }
    }
}

