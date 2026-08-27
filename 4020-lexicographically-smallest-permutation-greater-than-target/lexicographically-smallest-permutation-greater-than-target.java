class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] sCount = new int[26];
        
        for (int i = 0; i < n; i++) {
            sCount[s.charAt(i) - 'a']++;
        }
        
        int[][] prefixCounts = new int[n + 1][26];
        int maxPrefixLen = 0;
        
        for (int i = 0; i < n; i++) {
            int charIdx = target.charAt(i) - 'a';
            System.arraycopy(prefixCounts[i], 0, prefixCounts[i + 1], 0, 26);
            prefixCounts[i + 1][charIdx]++;
            
            if (prefixCounts[i + 1][charIdx] <= sCount[charIdx]) {
                maxPrefixLen = i + 1;
            } else {
                break;
            }
        }
        
        int startI = Math.min(maxPrefixLen, n - 1);
        for (int i = startI; i >= 0; i--) {
            int[] remCount = new int[26];
            for (int j = 0; j < 26; j++) {
                remCount[j] = sCount[j] - prefixCounts[i][j];
            }
            
            int targetCharIdx = target.charAt(i) - 'a';
            
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (remCount[c] > 0) {
                    remCount[c]--;
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(target, 0, i);
                    sb.append((char) ('a' + c));
                    
                    for (int j = 0; j < 26; j++) {
                        while (remCount[j] > 0) {
                            sb.append((char) ('a' + j));
                            remCount[j]--;
                        }
                    }
                    
                    return sb.toString();
                }
            }
        }
        
        return "";
    }
}