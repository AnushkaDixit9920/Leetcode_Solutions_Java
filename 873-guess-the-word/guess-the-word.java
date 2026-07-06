import java.util.*;

class Solution {
    public void findSecretWord(String[] words, Master master) {
        Set<String> liveWords = new HashSet<>();
        for (String element : words) {
            liveWords.add(element);
        }
        
        while (!liveWords.isEmpty()) {
            String queryWord = pickBestCandidate(liveWords);
            int hits = master.guess(queryWord);
            if (hits == 6) {
                return;
            }
            
            Set<String> retainedSet = new HashSet<>();
            for (String element : liveWords) {
                if (findOverlaps(element, queryWord) == hits) {
                    retainedSet.add(element);
                }
            }
            liveWords = retainedSet;
        }
    }
    
    private String pickBestCandidate(Set<String> options) {
        int[][] positionFreq = new int[6][26];
        for (String opt : options) {
            for (int k = 0; k < 6; k++) {
                positionFreq[k][opt.charAt(k) - 'a']++;
            }
        }
        
        int highestRank = -1;
        String pivot = "";
        
        for (String opt : options) {
            int weight = 0;
            for (int k = 0; k < 6; k++) {
                weight += positionFreq[k][opt.charAt(k) - 'a'];
            }
            if (weight > highestRank) {
                highestRank = weight;
                pivot = opt;
            }
        }
        return pivot;
    }
    
    private int findOverlaps(String lhs, String rhs) {
        int posMatches = 0;
        for (int k = 0; k < 6; k++) {
            if (lhs.charAt(k) == rhs.charAt(k)) {
                posMatches++;
            }
        }
        return posMatches;
    }
}