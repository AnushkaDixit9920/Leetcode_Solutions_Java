class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        
        int sum1 = 0, count1 = 0;
        int sum2 = 0, count2 = 0;
        
        for (int i = 0; i < half; i++) {
            char ch = num.charAt(i);
            if (ch == '?') {
                count1++;
            } else {
                sum1 += ch - '0';
            }
        }
        
        for (int i = half; i < n; i++) {
            char ch = num.charAt(i);
            if (ch == '?') {
                count2++;
            } else {
                sum2 += ch - '0';
            }
        }
        
        if ((count1 + count2) % 2 != 0) {
            return true;
        }
        
        return 2 * (sum1 - sum2) + 9 * (count1 - count2) != 0;
    }
}