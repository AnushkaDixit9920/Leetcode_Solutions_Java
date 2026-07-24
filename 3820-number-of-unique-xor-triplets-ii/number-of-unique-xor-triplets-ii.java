class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] inU = new boolean[2048];
        int uCount = 0;
        for (int num : nums) {
            if (!inU[num]) {
                inU[num] = true;
                uCount++;
            }
        }

        int[] U = new int[uCount];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (inU[i]) {
                U[idx++] = i;
            }
        }
        boolean[] inP = new boolean[2048];
        for (int i = 0; i < U.length; i++) {
            for (int j = i; j < U.length; j++) {
                inP[U[i] ^ U[j]] = true;
            }
        }
        boolean[] inS = new boolean[2048];
        for (int p = 0; p < 2048; p++) {
            if (inP[p]) {
                for (int w : U) {
                    inS[p ^ w] = true;
                }
            }
        }
        int ans = 0;
        for (boolean present : inS) {
            if (present) {
                ans++;
            }
        }

        return ans;
    }
}