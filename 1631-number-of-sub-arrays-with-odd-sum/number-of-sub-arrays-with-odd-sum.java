class Solution {
    public int numOfSubarrays(int[] arr) {
        final long MOD = 1000000007L;
        long evenCount = 1;
        long oddCount = 0;
        int isOdd = 0;

        for (int num : arr) {
            isOdd ^= (num & 1);
            if (isOdd == 1) {
                oddCount++;
            } else {
                evenCount++;
            }
        }

        return (int) ((evenCount * oddCount) % MOD);
    }
}