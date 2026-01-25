class Solution {
    public int minimumDifference(int[] nums, int k) {
        int ans=Integer.MAX_VALUE;
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0;i<n;i++){
            int j=i+k-1;
            if(j<n){
                int min=nums[i];
                int max=nums[j];
                ans=Math.min(max-min,ans);
            }
        }
        return ans;
    }
}