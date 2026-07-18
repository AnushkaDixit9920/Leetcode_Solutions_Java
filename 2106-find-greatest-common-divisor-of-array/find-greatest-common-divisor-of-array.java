class Solution {
    public int findGCD(int[] nums) {
        int largest=max(nums);
        int smallest=min(nums);
        return gcd(smallest,largest);
    }
    public int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
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
    public int min(int[]nums){
        int min=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<min){
                min=nums[i];
            }
        }
        return min;
    }
}