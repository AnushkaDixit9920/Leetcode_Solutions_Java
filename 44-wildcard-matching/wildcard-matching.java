class Solution {
    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0;
        int starIdx = -1;
        int sMatch = 0;
        int sLen = s.length(), pLen = p.length();
        while (sIdx < sLen) {
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            } 
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sMatch = sIdx;
                pIdx++;
            } 
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                sMatch++;
                sIdx = sMatch;
            } 
            else {
                return false;
            }
        }
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        return pIdx == pLen;
    }
}