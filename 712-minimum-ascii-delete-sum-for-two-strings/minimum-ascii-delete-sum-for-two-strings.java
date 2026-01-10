class Solution {
    int m, n;
    int [][]t;
    public int minimumDeleteSum(String s1, String s2) {
        m=s1.length();
        n=s2.length();
        t = new int[m][n];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }

        return solve(0,0,s1,s2);
    }
    public int solve(int i, int j, String s1, String s2){
        if(i>=m && j>=n){
            return 0;
        }
        if(i>=m){
            return s2.charAt(j)+solve(i,j+1,s1,s2);
        }else if(j>=n){
            return s1.charAt(i)+solve(i+1,j,s1,s2);
        }
        if (t[i][j] != -1) {
            return t[i][j];
        }

        if(s1.charAt(i)==s2.charAt(j)){
            return t[i][j]=solve(i+1,j+1,s1,s2);
        }
        
        int delete_s1_i=s1.charAt(i)+solve(i+1,j,s1,s2);
        int delete_s2_j=s2.charAt(j)+solve(i,j+1,s1,s2);
        return t[i][j]=Math.min(delete_s1_i,delete_s2_j);
    }
}