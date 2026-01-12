class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans=0;
        int n=points.length;
        for(int i=0;i<n-1;i++){
            int x1=points[i][0];
            int y1=points[i][1];
            int x2=points[i+1][0];
            int y2=points[i+1][1];
            int dx=Math.abs(x2-x1);
            int dy=Math.abs(y2-y1);
            ans+=(Math.min(dx,dy)+Math.abs(dx-dy));
        }
        return ans;
    }
}