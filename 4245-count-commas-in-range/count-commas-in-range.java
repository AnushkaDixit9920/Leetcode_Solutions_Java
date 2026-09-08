class Solution {
    public int countCommas(int n) {
        int total = 0;
        int threshold = 1000;
        while (n >= threshold) {
            total += (n - threshold + 1);
            if (threshold > Integer.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000;
        }
        return total;
    }
}