public class String_to_Integer_8 {

    public static void main(String[] args) {
        checkNum("42", 42);
        checkNum("    -42", -42);
        checkNum("4193 with words", 4193);
        checkNum("words and 987", 0);
        checkNum("-91283472332", -2147483648);
    }

    static void checkNum(String s, int expected) {
        int result = new Solution().myAtoi(s);

        if (expected != result) {
            System.out.println("" + s + "        " + expected + "          " + result );
        }
    }

    static class Solution {
        public int myAtoi(String str) {

            int value = 0;
            int sign = 1;
            char prev = ' ';

            for (char c : str.toCharArray()) {
                if (c == ' ') {
                    if (prev != ' ') {
                        break;
                    }
                } else if (c == '-' || c == '+') {
                    if (prev == ' ') {
                        sign = (c == '-' ? -1 : 1);
                    } else {
                        break;
                    }
                } else if (c >= '0' && c <= '9') {
                    int digit = c - '0';

                    int minusMaxValue = sign == 1 ? -Integer.MAX_VALUE : Integer.MIN_VALUE;
                    int maxValueBegin = -(minusMaxValue / 10);
                    int maxValueEnd   = -(minusMaxValue % 10);

                    if (maxValueBegin < value || (maxValueBegin == value && maxValueEnd <= digit)) {
                        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    }

                    value = value * 10 + digit;
                } else {
                    break;
                }

                prev = c;
            }

            return sign * value;
        }
    }
}

