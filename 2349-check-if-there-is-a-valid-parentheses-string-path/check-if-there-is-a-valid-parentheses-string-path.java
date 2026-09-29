class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        int maxDiff = (m + n) / 2;
        boolean[][][] visited = new boolean[m][n][maxDiff + 1];

        return dfs(0, 0, 0, grid, m, n, visited);
    }

    private boolean dfs(int r, int c, int diff, char[][] grid, int m, int n, boolean[][][] visited) {
        diff += (grid[r][c] == '(') ? 1 : -1;
        if (diff < 0) {
            return false;
        }
        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (diff > remainingSteps) {
            return false;
        }
        if (r == m - 1 && c == n - 1) {
            return diff == 0;
        }
        if (visited[r][c][diff]) {
            return false;
        }
        visited[r][c][diff] = true;
        if (c + 1 < n && dfs(r, c + 1, diff, grid, m, n, visited)) {
            return true;
        }
        if (r + 1 < m && dfs(r + 1, c, diff, grid, m, n, visited)) {
            return true;
        }
        return false;
    }
}