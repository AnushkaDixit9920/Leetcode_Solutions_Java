class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int[] item : items) {
            if (item[0] > maxFactor) {
                maxFactor = item[0];
            }
            if (item[1] < minPrice) {
                minPrice = item[1];
            }
        }

        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] bonus = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] > 0) {
                int multiplesCount = 0;
                for (int m = f; m <= maxFactor; m += f) {
                    multiplesCount += freq[m];
                }
                bonus[f] = multiplesCount - 1;
            }
        }

        int[] dp = new int[budget + 1];
        for (int[] item : items) {
            int f = item[0];
            int p = item[1];
            if (p <= budget) {
                int val = 1 + bonus[f];
                for (int w = budget; w >= p; w--) {
                    dp[w] = Math.max(dp[w], dp[w - p] + val);
                }
            }
        }

        for (int w = minPrice; w <= budget; w++) {
            dp[w] = Math.max(dp[w], dp[w - minPrice] + 1);
        }

        return dp[budget];
    }
}