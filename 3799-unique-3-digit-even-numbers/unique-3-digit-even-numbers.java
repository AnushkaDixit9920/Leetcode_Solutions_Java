class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }
        int ans = 0;
        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;
            count[d1]--;
            count[d2]--;
            count[d3]--;
            if (count[d1] >= 0 && count[d2] >= 0 && count[d3] >= 0) {
                ans++;
            }
            count[d1]++;
            count[d2]++;
            count[d3]++;
        }
        return ans;
    }
}