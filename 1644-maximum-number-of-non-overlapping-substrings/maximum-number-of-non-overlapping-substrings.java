
class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }
        List<int[]> validIntervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            int L = first[i];
            int R = last[i];
            boolean isValid = true;
            for (int j = L; j <= R; j++) {
                int c = s.charAt(j) - 'a';
                if (first[c] < L) {
                    isValid = false;
                    break;
                }
                R = Math.max(R, last[c]);
            }

            if (isValid) {
                validIntervals.add(new int[]{L, R});
            }
        }
        validIntervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        List<String> res = new ArrayList<>();
        int lastEnd = -1;
        for (int[] interval : validIntervals) {
            int L = interval[0];
            int R = interval[1];
            if (L > lastEnd) {
                res.add(s.substring(L, R + 1));
                lastEnd = R;
            }
        }
        return res;
    }
}
