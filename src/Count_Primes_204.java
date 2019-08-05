public class Count_Primes_204 {

    public static void main(String[] args) {
        check(10, 4);
    }

    static void check(int n, int expected) {
        int result = new Solution().countPrimes(n);

        if (expected != result) {
            System.out.println("" + n + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public int countPrimes(int n) {
            boolean[] composites = new boolean[n];

            int count = 0;
            for (int i = 2; i < composites.length; i++) {
                if (!composites[i]) {
                    count++;
                    for (int j = 2 * i; j < composites.length; j += i) {
                        composites[j] = true;
                    }
                }
            }
            return count;
        }
    }
}

