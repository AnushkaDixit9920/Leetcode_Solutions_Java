
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        k = k % totalElements;
        
        int[][] temp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int old1DIndex = i * n + j;
                int new1DIndex = (old1DIndex + k) % totalElements;
                
                int newRow = new1DIndex / n;
                int newCol = new1DIndex % n;
                
                temp[newRow][newCol] = grid[i][j];
            }
        }
  
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : temp) {
            List<Integer> listRow = new ArrayList<>();
            for (int val : row) {
                listRow.add(val);
            }
            result.add(listRow);
        }
        
        return result;
    }
}