class Solution {
    public boolean isTrionic(int[] nums) {
        boolean inc=false;
        int i=0;
        while(i<nums.length-1){
            if(nums[i+1]>nums[i]){
                inc=true;
                i++;
            }else{
                break;
            }
        }
        int j=i;
        boolean dec=false;
        while(j<nums.length-1){
            if(nums[j+1]<nums[j]){
                dec=true;
                j++;
            }else{
                break;
            }
        }
        boolean peakelement=inc && dec;
        boolean end=false;;
        while(j<nums.length-1){
            if(nums[j+1]>nums[j]){
                end=true;
                j++;
            }else{
                break;
            }
        }
        return peakelement && end && j==nums.length-1;
    }
}