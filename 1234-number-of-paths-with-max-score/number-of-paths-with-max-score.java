import java.util.*;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        final long MOD = 1_000_000_007L;
        
        char[][] b = new char[n][n];
        for (int i = 0; i < n; i++) b[i] = board.get(i).toCharArray();
        
        int[][] dp = new int[n][n];      // max sum achievable from (i,j) to S... actually from S to (i,j)
        long[][] cnt = new long[n][n];   // number of ways to achieve that max
        for (int[] row : dp) Arrays.fill(row, -1); // -1 = unreachable
        
        dp[n-1][n-1] = 0;   // S itself contributes 0
        cnt[n-1][n-1] = 1;
        
        int[] di = {1, 0, 1};   // down, right, down-right (i.e., the cells that can reach (i,j))
        int[] dj = {0, 1, 1};
        
        // Process cells so that (i+1,j), (i,j+1), (i+1,j+1) are ready before (i,j).
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if ((i == n-1 && j == n-1)) continue; // S already set
                if (b[i][j] == 'X') continue;         // obstacle: unreachable
                
                int best = -1;
                long ways = 0;
                
                for (int k = 0; k < 3; k++) {
                    int ni = i + di[k], nj = j + dj[k];
                    if (ni < n && nj < n && dp[ni][nj] >= 0) {
                        if (dp[ni][nj] > best) {
                            best = dp[ni][nj];
                            ways = cnt[ni][nj];
                        } else if (dp[ni][nj] == best) {
                            ways = (ways + cnt[ni][nj]) % MOD;
                        }
                    }
                }
                
                if (best >= 0) {
                    int val = (b[i][j] == 'E' || b[i][j] == 'S') ? 0 : (b[i][j] - '0');
                    dp[i][j] = best + val;
                    cnt[i][j] = ways;
                }
            }
        }
        
        if (dp[0][0] < 0) return new int[]{0, 0};
        return new int[]{dp[0][0], (int) cnt[0][0]};
    }
}