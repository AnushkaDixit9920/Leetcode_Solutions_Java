class Solution {
    Integer[][] dp;
    public int maxDotProduct(int[] nums1, int[] nums2) {
        dp = new Integer[nums1.length][nums2.length];
        return solve(0, 0, nums1, nums2);
    }

    private int solve(int i, int j, int[] a, int[] b) {
        if (i == a.length || j == b.length) {
            return Integer.MIN_VALUE;
        }

        if (dp[i][j] != null) return dp[i][j];

        int takeBoth = a[i] * b[j];
        int next = solve(i + 1, j + 1, a, b);
        if (next > 0) takeBoth += next;

        int skipA = solve(i + 1, j, a, b);
        int skipB = solve(i, j + 1, a, b);

        dp[i][j] = Math.max(takeBoth, Math.max(skipA, skipB));
        return dp[i][j];
    }
}
