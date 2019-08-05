import java.util.PriorityQueue;

public class Strong_Password_Checker_420 {

    public static void main(String[] args) {
//        check("", 6);
//        check("aaa111", 2);
//        check("1111111111", 3);
//        check("ABABABABABABABABABAB1", 2);
//        check("AAAAAABBBBBB123456789a", 4);
//        check("1234567890123456Baaaaa", 3);
        check("aaaaabbbb1234567890ABA", 3);
    }

    static void check(String s, int expected) {
        int result = new Solution().strongPasswordChecker(s);

        if (expected != result) {
            System.out.println("" + s + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public int strongPasswordChecker(String s) {
            int needDigits = 1;
            int needLower = 1;
            int needUpper = 1;

            int cleanTriples = 0;
            int protectedTriples = 0;
            int doubleProtectecTriples = 0;

            char prev = 0;
            int count = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c >= '0' && c <= '9') {
                    needDigits = 0;
                } else if (c >= 'a' && c <= 'z') {
                    needLower = 0;
                } else if (c >= 'A' && c <= 'Z') {
                    needUpper = 0;
                }

                if (c == prev) {
                    count++;
                }

                if (c != prev || i == chars.length - 1) {
                    if (count >= 3) {

                        int rest = count % 3;
                        if (rest == 2) {
                            doubleProtectecTriples++;
                        } else if (rest == 1) {
                            protectedTriples++;
                        } else {
                            cleanTriples++;
                        }

                        doubleProtectecTriples += count / 3 - 1;
                    }
                    count = 1;
                }

                prev = c;
            }

            int needToChange = needDigits + needLower + needUpper;
            int triples = cleanTriples + protectedTriples + doubleProtectecTriples;

            if (s.length() < 6) {
                return Math.max(triples, Math.max(needToChange, 6 - s.length()));
            } else if (s.length() <= 20) {
                return Math.max(needToChange, triples);
            }

            int needToRemove = s.length() - 20;
            int restToRemove = needToRemove;

            int removeCleanTriples = Math.min(restToRemove, cleanTriples);
            triples -= removeCleanTriples;
            restToRemove -= removeCleanTriples;

            int removeProtectedTriples = Math.min(restToRemove, protectedTriples * 2) / 2;
            triples -= removeProtectedTriples;
            restToRemove -= removeProtectedTriples * 2;

            int removeDoubleProtectedTriples = Math.min(restToRemove, doubleProtectecTriples * 3) /3;
            triples -= removeDoubleProtectedTriples;

            return needToRemove + Math.max(needToChange, triples);
        }
    }
}

