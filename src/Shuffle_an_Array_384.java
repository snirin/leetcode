import java.util.Random;

public class Shuffle_an_Array_384 {

    public static void main(String[] args) {
        check(new int[]{1,2,3,4,5});
//        check(new int[]{1,2}, 1);
    }

    static void check(int[] nums) {
        int[] result = new Solution(nums).shuffle();
        System.out.println();
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    static class Solution {

        private final int [] nums;

        public Solution(int[] nums) {
            this.nums = nums;
        }

        /** Resets the array to its original configuration and return it. */
        public int[] reset() {
            return nums;
        }

        /** Returns a random shuffling of the array. */
        public int[] shuffle() {
            int [] res = new int[nums.length];
            int cur = 0;
            boolean[] used = new boolean[nums.length];
            Random rand = new Random();
            for (int i = nums.length - 1; i >= 0; i--) {

                int j = i == 0 ? 0 : rand.nextInt(i);

                int k = 0;
                while (k < j || used[k]) {
                    if (used[k]) {
                        j++;
                    }
                    k++;
                }

                used[k] = true;
                res[cur++] = nums[k];
            }

            return res;
        }
    }
}

