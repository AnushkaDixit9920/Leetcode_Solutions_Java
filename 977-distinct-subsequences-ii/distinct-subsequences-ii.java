class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] dp = new int[26];
        int total = 0;
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            int prev = dp[idx];
            dp[idx] = (total + 1) % MOD;
            total = (int) (((long) total + dp[idx] - prev + MOD) % MOD);
        }
        return total;
    }
}