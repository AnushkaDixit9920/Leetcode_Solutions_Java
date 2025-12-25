class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long ans=0;
        int curr=0;
        Arrays.sort(happiness);
        for(int i=happiness.length-1;i>=0;i--){
            if(k==0)return ans;
            happiness[i]=happiness[i]-curr;
            curr++;
            if(happiness[i]<0){
                happiness[i]=0;
            }
            ans+=happiness[i];
            k--;
        }
        return ans;
    }
}