class Solution {
    public long maximumScore(int[] nums) {
        int n=nums.length;
        long[]pref= new long[n];
        pref[0]=nums[0];
        for(int i=1;i<n;i++){
            pref[i]=pref[i-1]+nums[i];
        }
        int[]suffixmin=new int[n];
        suffixmin[n-1]=nums[n-1];
        for(int i=n-2;i>=0;i--){
            suffixmin[i]=Math.min(nums[i],suffixmin[i+1]);
        }
        long maxscore=Long.MIN_VALUE;
        for(int i=0;i<nums.length-1;i++){
            maxscore=Math.max(maxscore,(pref[i]-suffixmin[i+1]));
        }
        return maxscore;
    }
}