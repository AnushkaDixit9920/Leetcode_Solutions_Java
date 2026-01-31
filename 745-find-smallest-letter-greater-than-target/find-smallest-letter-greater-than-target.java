class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int t=target;
        for(int i=0;i<letters.length;i++){
            char ch=letters[i];
            int curr=ch;
            if(curr>t){
                return ch;
            }
        }
        return letters[0];
    }
}