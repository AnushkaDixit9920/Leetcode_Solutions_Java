import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    long islandSum = grid[r][c];
                    grid[r][c] = 0;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{r, c});
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int currR = curr[0];
                        int currC = curr[1];
                        for (int[] dir : dirs) {
                            int nr = currR + dir[0];
                            int nc = currC + dir[1];
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                islandSum += grid[nr][nc];
                                grid[nr][nc] = 0;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}