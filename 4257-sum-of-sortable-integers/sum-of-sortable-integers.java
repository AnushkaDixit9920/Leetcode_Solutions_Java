
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        int totalSum = 0;
        for (int k : divisors) {
            if (isSortable(nums, n, k)) {
                totalSum += k;
            }
        }
        return totalSum;
    }
    private boolean isSortable(int[] nums, int n, int k) {
        int prevMax = -1;
        for (int start = 0; start < n; start += k) {
            int drops = 0;
            int dropIdx = -1;
            for (int j = 0; j < k; j++) {
                int curr = nums[start + j];
                int nxt = nums[start + (j + 1) % k];
                if (curr > nxt) {
                    drops++;
                    if (drops > 1) {
                        return false;
                    }
                    dropIdx = j;
                }
            }
            int mn, mx;
            if (drops == 0) {
                mn = nums[start];
                mx = nums[start];
            } else {
                mn = nums[start + (dropIdx + 1) % k];
                mx = nums[start + dropIdx];
            }
            if (prevMax != -1 && mn < prevMax) {
                return false;
            }
            prevMax = mx;
        }
        return true;
    }
}