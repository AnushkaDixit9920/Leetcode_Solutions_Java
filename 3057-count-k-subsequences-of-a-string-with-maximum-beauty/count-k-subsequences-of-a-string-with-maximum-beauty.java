import java.util.*;
class Solution {
    private static final int MOD = 1_000_000_007;
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        List<Integer> vals = new ArrayList<>();
        for (int f : freq) {
            if (f > 0) {
                vals.add(f);
            }
        }
        if (vals.size() < k) {
            return 0;
        }
        vals.sort(Collections.reverseOrder());
        int targetVal = vals.get(k - 1);
        long ans = 1;
        int numGreater = 0;
        int cnt = 0;
        for (int f : vals) {
            if (f > targetVal) {
                ans = (ans * f) % MOD;
                numGreater++;
            } else if (f == targetVal) {
                cnt++;
            }
        }
        int need = k - numGreater;
        long ways = comb(cnt, need) % MOD;
        ans = (ans * ways) % MOD;
        long power = power(targetVal, need, MOD);
        ans = (ans * power) % MOD;
        return (int) ans;
    }

    private long comb(int n, int r) {
        if (r < 0 || r > n) return 0;
        long res = 1;
        for (int i = 1; i <= r; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}