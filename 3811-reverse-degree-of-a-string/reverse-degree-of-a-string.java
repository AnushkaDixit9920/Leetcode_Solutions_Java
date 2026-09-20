class Solution {
    public int reverseDegree(String s) {
        int totalDegree = 0;
        for (int i = 0; i < s.length(); i++) {
            int reversedAlphabetVal = 'z' - s.charAt(i) + 1;
            totalDegree += reversedAlphabetVal * (i + 1);
        }
        return totalDegree;
    }
}