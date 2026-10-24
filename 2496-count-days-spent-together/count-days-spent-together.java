class Solution {
    int days[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int a1 = convert(arriveAlice);
        int a2 = convert(leaveAlice);
        int b1 = convert(arriveBob);
        int b2 = convert(leaveBob);
        int left = Math.max(a1, b1);
        int right = Math.min(a2, b2);
        int ans = right - left + 1;
        return ans < 0 ? 0 : ans;
    }
    private int convert(String s) {
        int m = Integer.parseInt(s.substring(0, 2));
        int d = Integer.parseInt(s.substring(3));
        int res = d;
        for (int i = 0; i < m - 1; i++) {
            res += days[i];
        }
        return res;
    }
}