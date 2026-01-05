class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum=0;
        int countnegative=0;
        int minabsval=Integer.MAX_VALUE;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                sum+=Math.abs(matrix[i][j]);
                minabsval=Math.min(Math.abs(matrix[i][j]),minabsval);
                if(matrix[i][j]<0){
                    countnegative++;
                }
            }
        }
        if(countnegative%2==0){
            return sum;
        }
        return sum-2*minabsval;
    }
}