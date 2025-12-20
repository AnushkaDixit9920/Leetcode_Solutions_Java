class Solution {
    public int minDeletionSize(String[] strs) {
        int count=0;
        int n=strs.length;
        int i=0;
        while(i<strs[0].length()){
            int j=0;
            while(j<strs.length-1){
                String str=strs[j];
                String next=strs[j+1];
                int x=str.charAt(i)-'a';
                int y=next.charAt(i)-'a';
                if(x>y){
                    count++;
                    i++;
                    break;
                }
                j++;
            }
            if(j==strs.length-1){
                i++;
            }
        }
        return count;
    }
}