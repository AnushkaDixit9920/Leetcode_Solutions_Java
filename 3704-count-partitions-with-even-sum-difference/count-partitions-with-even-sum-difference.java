class Solution {
    public int countPartitions(int[] nums) {
        int count=0;
        int[]pref= new int[nums.length];
        pref[0]=nums[0];
        for(int j=1;j<pref.length;j++){
            pref[j]=nums[j]+pref[j-1];
        }
        for(int i=0;i<pref.length-1;i++){
            int left=pref[i];
            int right=pref[pref.length-1]-pref[i];
            int diff=Math.abs(left-right);
            if(diff%2==0){
                count++;
            }
        }
        return count;
    }
}