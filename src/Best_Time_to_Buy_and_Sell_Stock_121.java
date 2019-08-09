public class Best_Time_to_Buy_and_Sell_Stock_121 {

    public static void main(String[] args) {
        check(new int[]{7,1,5,3,6,4}, 5);
        check(new int[]{1,2}, 1);
    }

    static void check(int[] prices, int expected) {
        int result = new Solution().maxProfit(prices);

        if (expected != result) {
            System.out.println("" + prices + "        " + expected + "          " + result);
        }
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int length = prices.length;

            int[] minLefts = new int[length];
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < length; i++) {
                min = Math.min(min, prices[i]);
                minLefts[i] = min;
            }

            int[] maxRights = new int[length];
            int max = Integer.MIN_VALUE;
            for (int i = length - 1; i >= 0; i--) {
                max = Math.max(max, prices[i]);
                maxRights[i] = max;
            }

            int profit = 0;
            for (int i = 0; i < length; i++) {
                profit = Math.max(profit, maxRights[i] - minLefts[i]);
            }

            return profit;
        }
    }
}

