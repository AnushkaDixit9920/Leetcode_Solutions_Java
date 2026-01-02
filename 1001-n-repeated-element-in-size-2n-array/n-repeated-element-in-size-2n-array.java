class Solution {
    public int repeatedNTimes(int[] nums) {
        int count=nums.length/2;
        HashMap<Integer,Integer>mp= new HashMap<>();
        for(int i=0;i<nums.length;i++){
            mp.put(nums[i],mp.getOrDefault(nums[i],0)+1);
        }
        for(var e:mp.entrySet()){
            if(e.getValue()==count){
                return e.getKey();
            }
        }
        return -1;
    }
}