class Solution {
    public int countNegatives(int[][] grid) {
        int count=0;
        int m=grid.length;
        int n=grid[0].length;
        for(int i=0;i<m;i++){
            int index=binarysearch(grid[i]);
            if(index!=-1){
                count+=(grid[i].length-index);
            }
        }
        return count;

    }
    public int binarysearch(int[]arr){
        int low=0;
        int high=arr.length-1;
        int ans=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]<0){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
}