class Solution {
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        int MOD = 1000000007;
        int[][] gcdTable = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j <= maxVal; j++) {
                if (i == 0) {
                    gcdTable[i][j] = j;
                } else if (j == 0) {
                    gcdTable[i][j] = i;
                } else {
                    gcdTable[i][j] = gcd(i, j);
                }
            }
        }
        
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;
        
        for (int num : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) {
                        continue;
                    }
                    
                    int cnt = dp[g1][g2];
                    int ng1 = gcdTable[g1][num];
                    int ng2 = gcdTable[g2][num];
                    
                    int selfMult = 1;
                    if (ng1 == g1) {
                        selfMult++;
                    }
                    if (ng2 == g2) {
                        selfMult++;
                    }
                    
                    nextDp[g1][g2] = (nextDp[g1][g2] + (int)(((long)cnt * selfMult) % MOD)) % MOD;
                    
                    if (ng1 != g1) {
                        nextDp[ng1][g2] = (nextDp[ng1][g2] + cnt) % MOD;
                    }
                    if (ng2 != g2) {
                        nextDp[g1][ng2] = (nextDp[g1][ng2] + cnt) % MOD;
                    }
                }
            }
            dp = nextDp;
        }
        
        long ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }
}