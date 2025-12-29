class Solution {
    public int countNegatives(int[][] grid) {
        int count=0;
        int m=grid.length-1;
        int n=0;
        while(m>=0 && n<grid[0].length){
            if(grid[m][n]>=0){
                n++;
            }else if( grid[m][n]<0){
                count+=grid[0].length-n;
                m--;
            }
        }
        return count;
    }
}