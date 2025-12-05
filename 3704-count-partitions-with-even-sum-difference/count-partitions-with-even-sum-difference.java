class Solution {
    public int countPartitions(int[] nums) {
        int count=0;
        int totalsum=0;
        for(int i=0;i<nums.length;i++){
            totalsum+=nums[i];
        }
        int leftsum=0;
        for(int j=0;j<nums.length-1;j++){
            leftsum+=nums[j];
            int rightsum=totalsum-leftsum;
            int diff=Math.abs(rightsum-leftsum);
            if(diff%2==0){
                count++;
            }
        }
        return count;
    }
}