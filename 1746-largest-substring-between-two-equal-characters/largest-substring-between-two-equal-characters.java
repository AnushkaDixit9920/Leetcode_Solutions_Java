class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int maxlen=-1;
        for(int i=0;i<s.length();i++){
            char curr=s.charAt(i);
            for(int j=i;j<s.length();j++){
                if(s.charAt(j)==curr){
                    maxlen=Math.max(maxlen,j-i-1);
                }
            }
        }
        return maxlen;
    }
}