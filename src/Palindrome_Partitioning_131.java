import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Palindrome_Partitioning_131 {

    public static void main(String[] args) {
        check("aab", asList(asList("a", "a", "b"), asList("aa", "b")));
    }

    static void check(String s, List<List<String>> expected) {
        List<List<String>> result = new Solution().partition(s);

        if (expected != result) {
            System.out.println("" + s + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public List<List<String>> partition(String s) {
            if (s.isEmpty()) {
                return new ArrayList<>(Collections.singletonList(Collections.emptyList()));
            }

            List<List<String>> res = new ArrayList<>();
            for (int i = 1; i <= s.length(); i++) {
                String sub = s.substring(0, i);

                if (isPalindrome(sub)) {
                    for (List<String> list : partition(s.substring(i))) {
                        List<String> newList = new ArrayList<>(list);
                        newList.add(0, sub);
                        res.add(newList);
                    }
                }

            }
            return res;
        }

        private boolean isPalindrome(String s) {
            for (int i = 0; i < s.length() / 2; i++) {
                if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }
}

