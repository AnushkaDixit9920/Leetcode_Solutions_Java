import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        long[] pairs = new long[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = ((long) nums[i] << 32) | (i & 0xFFFFFFFFL);
        }
        Arrays.sort(pairs);

        int[] sa = new int[n];
        int[] rk = new int[n];
        int[] tmp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = (int) pairs[i];
        }

        rk[sa[0]] = 0;
        int classes = 1;
        for (int i = 1; i < n; i++) {
            if (nums[sa[i]] != nums[sa[i - 1]]) {
                classes++;
            }
            rk[sa[i]] = classes - 1;
        }

        int[] cnt = new int[n];
        for (int k = 1; k < n && classes < n; k <<= 1) {
            int idx = 0;
            for (int i = n - k; i < n; i++) {
                tmp[idx++] = i;
            }
            for (int i = 0; i < n; i++) {
                if (sa[i] >= k) {
                    tmp[idx++] = sa[i] - k;
                }
            }

            Arrays.fill(cnt, 0, classes, 0);
            for (int i = 0; i < n; i++) {
                cnt[rk[tmp[i]]]++;
            }
            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                sa[--cnt[rk[tmp[i]]]] = tmp[i];
            }

            tmp[sa[0]] = 0;
            classes = 1;
            for (int i = 1; i < n; i++) {
                int cur1 = rk[sa[i]];
                int prev1 = rk[sa[i - 1]];
                int cur2 = (sa[i] + k < n) ? rk[sa[i] + k] : -1;
                int prev2 = (sa[i - 1] + k < n) ? rk[sa[i - 1] + k] : -1;
                if (cur1 != prev1 || cur2 != prev2) {
                    classes++;
                }
                tmp[sa[i]] = classes - 1;
            }
            int[] swap = rk;
            rk = tmp;
            tmp = swap;
        }

        for (int i = 0; i < n; i++) {
            rk[sa[i]] = i;
        }

        int[] lcp = new int[n - 1];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (rk[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = sa[rk[i] + 1];
            while (i + k < n && j + k < n && nums[i + k] == nums[j + k]) {
                k++;
            }
            lcp[rk[i]] = k;
            if (k > 0) {
                k--;
            }
        }

        int ans = n;
        for (int i = 0; i < n; i++) {
            int maxLcp = 0;
            if (i > 0) maxLcp = Math.max(maxLcp, lcp[i - 1]);
            if (i + 1 < n) maxLcp = Math.max(maxLcp, lcp[i]);
            int suffixLen = n - sa[i];
            if (suffixLen > maxLcp) {
                ans = Math.min(ans, maxLcp + 1);
            }
        }
        return ans;
    }
}