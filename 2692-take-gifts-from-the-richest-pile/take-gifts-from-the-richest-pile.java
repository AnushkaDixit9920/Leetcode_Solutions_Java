class Solution {
    public long pickGifts(int[] gifts, int k) {
        long ans=0;
        while(k>0){
            Arrays.sort(gifts);
            double end=Math.sqrt(gifts[gifts.length-1]);
            gifts[gifts.length-1]=(int)end;
            k--;
        }
        for(int i=0;i<gifts.length;i++){
            ans+=gifts[i];
        }
        return ans;
    }
}