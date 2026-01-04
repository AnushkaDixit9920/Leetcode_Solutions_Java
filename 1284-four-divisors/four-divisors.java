class Solution {
    public int sumFourDivisors(int[] nums) {
        int sum=0;
        for(int i=0;i<nums.length;i++){
            int currsum=0;
            currsum=currsum+1+nums[i];
            int count=2;
            for(int j=2;j*j<=nums[i];j++){
                if(nums[i]%j==0){
                    currsum+=j;
                    count++;
                    if(j!=nums[i]/j){
                        currsum+=nums[i]/j;
                        count++;
                    }
                }
            }
            if(count==4){
                sum+=currsum;
            }

        }
        return sum;
        
    }
}