
public class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        int[][] dpSum = new int[n][n];
        int[][] dpCount = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dpSum[i][j] = -1;
            }
        }
        dpSum[n - 1][n - 1] = 0;
        dpCount[n - 1][n - 1] = 1;
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
 
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                
                if (i == n - 1 && j == n - 1) {
                    continue;
                }
                if (board.get(i).charAt(j) == 'X') {
                    continue;
                }
                
                int maxPrev = -1;
                int countPrev = 0;
                
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    
                    if (ni < n && nj < n && dpSum[ni][nj] != -1) {
                        if (dpSum[ni][nj] > maxPrev) {
                            maxPrev = dpSum[ni][nj];
                            countPrev = dpCount[ni][nj];
                        } else if (dpSum[ni][nj] == maxPrev) {
                            countPrev = (countPrev + dpCount[ni][nj]) % MOD;
                        }
                    }
                }
                
                if (maxPrev != -1) {
                    char c = board.get(i).charAt(j);
                    int currentVal = (c == 'E') ? 0 : (c - '0');
                    dpSum[i][j] = maxPrev + currentVal;
                    dpCount[i][j] = countPrev;
                }
            }
        }
        
        if (dpSum[0][0] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{dpSum[0][0], dpCount[0][0]};
    }
}