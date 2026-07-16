
class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int mx = 0;
        
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }
        
        Arrays.sort(prefixGcd);
        
        long totalSum = 0;
        for (int i = 0; i < n / 2; i++) {
            totalSum += gcd(prefixGcd[i], prefixGcd[n - i - 1]);
        }
        
        return totalSum;
    }
}