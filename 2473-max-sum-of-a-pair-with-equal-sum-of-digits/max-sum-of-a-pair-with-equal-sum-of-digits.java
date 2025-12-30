class Solution {
    public int maximumSum(int[] nums) {
        HashMap<Integer,ArrayList<Integer>>mp= new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int value=sum(nums[i]);
            if(mp.containsKey(value)){
                mp.get(value).add(nums[i]);
            }else{
                ArrayList<Integer>ll= new ArrayList<>();
                ll.add(nums[i]);
                mp.put(value,ll);
            }
        }
        int ans=-1;
        for(var e:mp.entrySet()){
            int now=0;
            Collections.sort(e.getValue());
            ArrayList<Integer>LL= e.getValue();
            if(LL.size()>=2){
                now+=LL.get(LL.size()-1);
                now+=LL.get(LL.size()-2);
                ans=Math.max(ans,now);
            }
            

        }
        return ans;
    }
    public int sum(int x){
        int sum=0;
        while(x>0){
            sum+=x%10;
            x=x/10;
        }
        return sum;
    }
}