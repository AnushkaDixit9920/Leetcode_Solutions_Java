class Solution {
    public int maxTotalValue(int[] value, int[] decay, int m) {
        long actual_m = Math.min((long) m, count(1, value, decay));
        if (actual_m == 0) return 0;
        long low = 1, high = 1_000_000_000, X = 1;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (count(mid, value, decay) >= actual_m) {
                X = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        long total_taken_count = 0, total_sum = 0, MOD = 1_000_000_007;
        for (int i = 0; i < value.length; i++) {
            if (value[i] >= X + 1) {
                long k = (value[i] - (X + 1)) / decay[i] + 1;
                total_taken_count += k;
                long term1 = (k % MOD) * (value[i] % MOD) % MOD;
                long term2 = (k % MOD) * ((k - 1) % MOD) % MOD * 500000004L % MOD * (decay[i] % MOD) % MOD;
                total_sum = (total_sum + term1 - term2 + MOD) % MOD;
            }
        }
        long remaining = actual_m - total_taken_count;
        return (int) ((total_sum + (remaining % MOD) * (X % MOD)) % MOD);
    }

    private long count(long X, int[] value, int[] decay) {
        long ans = 0;
        for (int i = 0; i < value.length; i++) {
            if (value[i] >= X) ans += (value[i] - X) / decay[i] + 1;
        }
        return ans;
    }
}