class Solution {
    public int minMoves(int[] nums) {
        int MAX=max(nums);
        int count=0;
        for(int i=0;i<nums.length;i++){
            while(nums[i]!=MAX){
                nums[i]++;
                count++;
            }
        }
        return count;
    }
    public int max(int[]nums){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max){
                max=nums[i];
            }
        }
        return max;
    }
}