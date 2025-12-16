class Solution {
    public long getDescentPeriods(int[] prices) {
        boolean continuous=false;
        long ans=prices.length;
        int prev=0;
        for(int i=1;i<prices.length;i++){
            if((prices[i]==prices[i-1]-1) && continuous==true){
                ans+=2;
                int len=i-prev+1;
                ans+=(len-3);
            }else if(prices[i]==prices[i-1]-1){
                ans+=1;
                continuous=true;
                prev=i-1;
            }else{
                continuous=false;
            }
        }
        return ans;
    }
}