
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        BitSet[] dp = new BitSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new BitSet(1024);
        }
        dp[0].set(grid[0][0]);
        
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            for (int x = dp[j - 1].nextSetBit(0); x >= 0; x = dp[j - 1].nextSetBit(x + 1)) {
                dp[j].set(x ^ val);
            }
        }
        
        for (int i = 1; i < m; i++) {
            BitSet newCol0 = new BitSet(1024);
            int val0 = grid[i][0];
            for (int x = dp[0].nextSetBit(0); x >= 0; x = dp[0].nextSetBit(x + 1)) {
                newCol0.set(x ^ val0);
            }
            dp[0] = newCol0;
            
            for (int j = 1; j < n; j++) {
                BitSet union = (BitSet) dp[j].clone();
                union.or(dp[j - 1]);
                
                BitSet nextSet = new BitSet(1024);
                int val = grid[i][j];
                for (int x = union.nextSetBit(0); x >= 0; x = union.nextSetBit(x + 1)) {
                    nextSet.set(x ^ val);
                }
                dp[j] = nextSet;
            }
        }
        
        return dp[n - 1].nextSetBit(0);
    }
}