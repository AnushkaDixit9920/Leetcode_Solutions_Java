class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 2;

        for (int i = 0; i < n; i++) {
            int prod = 1;
            int curGcd = 0;
            int curLcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];
                prod *= x;
                if (prod > 25200) {
                    break;
                }

                curGcd = gcd(curGcd, x);
                curLcm = (curLcm * x) / gcd(curLcm, x);

                if (prod == curGcd * curLcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}