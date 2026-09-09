class Solution {
    public int minCut(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i;
        }

        for (int mid = 0; mid < n; mid++) {
            for (int left = mid, right = mid; left >= 0 && right < n && arr[left] == arr[right]; left--, right++) {
                int cuts = (left == 0) ? 0 : dp[left - 1] + 1;
                dp[right] = Math.min(dp[right], cuts);
            }

            for (int left = mid, right = mid + 1; left >= 0 && right < n && arr[left] == arr[right]; left--, right++) {
                int cuts = (left == 0) ? 0 : dp[left - 1] + 1;
                dp[right] = Math.min(dp[right], cuts);
            }
        }
        return dp[n - 1];
    }
}