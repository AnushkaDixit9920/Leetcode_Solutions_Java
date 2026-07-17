class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max_val = 0;
        for (int num : nums) {
            if (num > max_val) {
                max_val = num;
            }
        }
        
        int[] cnt = new int[max_val + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        
        long[] cnt_multiple = new long[max_val + 1];
        for (int g = 1; g <= max_val; ++g) {
            for (int multiple = g; multiple <= max_val; multiple += g) {
                cnt_multiple[g] += cnt[multiple];
            }
        }
        
        long[] F = new long[max_val + 1];
        for (int g = max_val; g >= 1; --g) {
            long total_pairs = cnt_multiple[g] * (cnt_multiple[g] - 1) / 2;
            long subtracted = 0;
            for (int multiple = 2 * g; multiple <= max_val; multiple += g) {
                subtracted += F[multiple];
            }
            F[g] = total_pairs - subtracted;
        }
        
        long[] pref = new long[max_val + 1];
        for (int g = 1; g <= max_val; ++g) {
            pref[g] = pref[g - 1] + F[g];
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            long q = queries[i];
            int low = 1, high = max_val;
            int res = max_val;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (pref[mid] > q) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            ans[i] = res;
        }
        
        return ans;
    }
}