class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];
        for (int num : nums) {
            int val = num % k;
            long[] newDp = new long[k];
            newDp[val]++;
            for (int v = 0; v < k; v++) {
                if (dp[v] > 0) {
                    newDp[(v * val) % k] += dp[v];
                }
            }
            for (int rem = 0; rem < k; rem++) {
                result[rem] += newDp[rem];
            }
            dp = newDp;
        }
        return result;
    }
}