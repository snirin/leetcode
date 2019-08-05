public class Valid_Number_65 {

    public static void main(String[] args) {
//        "0" => true
//        " 0.1 " => true
//        "abc" => false
//        "1 a" => false
//        "2e10" => true
//        " -90e3   " => true
//        " 1e" => false
//        "e3" => false
//        " 6e-1" => true
//        " 99e2.5 " => false
//        "53.5e93" => true
//        " --6 " => false
//        "-+3" => false
//        "95a54e53" => false

        checkNum("0", true);
        checkNum(" 0.1 ", true);
        checkNum("abc", false);
        checkNum("1 a", false);
        checkNum("2e10", true);
        checkNum(" -90e3   ", true);
        checkNum(" 1e", false);
        checkNum("e3", false);
        checkNum(" 6e-1", true);
        checkNum(" 99e2.5 ", false);
        checkNum("53.5e93", true);
        checkNum(" --6 ", false);
        checkNum("-+3", false);
        checkNum("95a54e53", false);
        checkNum(".1", true);
        checkNum("3.", true);
    }

    static void checkNum(String s, boolean expected) {
        boolean result = new Solution().isNumber(s);

        if (expected != result) {
            System.out.println("" + s + "        " + result);
        }
    }

    static class Solution {
        public boolean isNumber(String s) {
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[+-]?(\\d+(\\.\\d+)?|(\\.\\d+)|(\\d+\\.))(e[+-]?\\d+)?");
            return pattern.matcher(s.trim()).matches();
        }
    }
}

