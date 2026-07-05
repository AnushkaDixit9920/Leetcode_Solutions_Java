import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size(), MOD = 1_000_000_007;
        int[][] dpSum = new int[n][n], dpCount = new int[n][n];
        dpCount[n - 1][n - 1] = 1; // Start cell
        
        int[] dr = {1, 0, 1}, dc = {0, 1, 1}; // Up, Left, Up-Left transitions
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if ((i == n - 1 && j == n - 1) || board.get(i).charAt(j) == 'X') continue;
                
                int max = -1, count = 0;
                for (int d = 0; d < 3; d++) {
                    int r = i + dr[d], c = j + dc[d];
                    if (r < n && c < n && dpCount[r][c] > 0) {
                        if (dpSum[r][c] > max) {
                            max = dpSum[r][c];
                            count = dpCount[r][c];
                        } else if (dpSum[r][c] == max) {
                            count = (count + dpCount[r][c]) % MOD;
                        }
                    }
                }
                if (max != -1) {
                    char c = board.get(i).charAt(j);
                    dpSum[i][j] = max + (c == 'E' ? 0 : c - '0');
                    dpCount[i][j] = count;
                }
            }
        }
        return new int[]{dpSum[0][0], dpCount[0][0]};
    }
}