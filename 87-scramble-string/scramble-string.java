class Solution {
    private int[][][] memo;
    private String s1, s2;
    public boolean isScramble(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        int n = s1.length();
        memo = new int[n][n][n + 1];
        return solve(0, 0, n);
    }
    private boolean solve(int i1, int i2, int len) {
        if (memo[i1][i2][len] != 0) {
            return memo[i1][i2][len] == 1;
        }
        if (s1.substring(i1, i1 + len).equals(s2.substring(i2, i2 + len))) {
            memo[i1][i2][len] = 1;
            return true;
        }
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s1.charAt(i1 + i) - 'a']++;
            count[s2.charAt(i2 + i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                memo[i1][i2][len] = 2;
                return false;
            }
        }
        for (int k = 1; k < len; k++) {
            if (solve(i1, i2, k) && solve(i1 + k, i2 + k, len - k)) {
                memo[i1][i2][len] = 1;
                return true;
            }
            if (solve(i1, i2 + len - k, k) && solve(i1 + k, i2, len - k)) {
                memo[i1][i2][len] = 1;
                return true;
            }
        }
        memo[i1][i2][len] = 2;
        return false;
    }
}