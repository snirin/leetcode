import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Top_K_Frequent_Elements_347 {

    public static void main(String[] args) {
        check(new int[]{1,1,1,2,2,3}, 2, asList(1,2));
    }

    static void check(int[] nums, int k, List<Integer> expected) {
        List<Integer>  result = new Solution().topKFrequent(nums, k);

        if (expected != result) {
            System.out.println("" + nums + "   " + k + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public List<Integer> topKFrequent(int[] nums, int k) {
            return Arrays.stream(nums)
                    .boxed()
                    .collect(groupingBy(o -> o, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().intValue() - e1.getValue().intValue())
                    .map(Map.Entry::getKey)
                    .limit(k)
                    .collect(toList());
        }
    }
}

