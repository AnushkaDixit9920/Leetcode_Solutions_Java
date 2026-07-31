
class Solution {
    public int minimumDifference(int[] nums) {
        int totalLen = nums.length;
        int n = totalLen / 2;

        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = nums[i];
            right[i] = nums[n + i];
        }

        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            int k = Integer.bitCount(mask);
            int lSum = 0;
            int rSum = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lSum += left[i];
                    rSum += right[i];
                }
            }

            leftSums.get(k).add(lSum);
            rightSums.get(k).add(rSum);
        }

        for (int k = 0; k <= n; k++) {
            Collections.sort(rightSums.get(k));
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int minDiff = Integer.MAX_VALUE;

        for (int k = 0; k <= n; k++) {
            List<Integer> rList = rightSums.get(n - k);

            for (int a : leftSums.get(k)) {
                int targetKey = (totalSum - 2 * a) / 2;
                int pos = Collections.binarySearch(rList, targetKey);

                int idx = pos >= 0 ? pos : -pos - 1;

                if (idx < rList.size()) {
                    minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * (a + rList.get(idx))));
                }
                if (idx > 0) {
                    minDiff = Math.min(minDiff, Math.abs(totalSum - 2 * (a + rList.get(idx - 1))));
                }
            }
        }

        return minDiff;
    }
}