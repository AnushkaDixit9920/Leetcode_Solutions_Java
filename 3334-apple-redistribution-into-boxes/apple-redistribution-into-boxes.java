class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalapples=0;
        for(int i=0;i<apple.length;i++){
            totalapples+=apple[i];
        }
        Arrays.sort(capacity);
        int count=0;
        for(int j=capacity.length-1;j>=0;j--){
            if(totalapples<=0){
                return count;
            }else{
                count++;
            }
            totalapples=totalapples-capacity[j];
        }
        return count;
    }
}