class Solution {
    private Boolean[] memo;
    private int maxNum;
    private int target;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (sum < desiredTotal) return false;

        maxNum = maxChoosableInteger;
        target = desiredTotal;
        memo = new Boolean[1 << maxChoosableInteger];

        return canWin(0, 0);
    }

    private boolean canWin(int mask, int currentTotal) {
        if (memo[mask] != null) {
            return memo[mask];
        }

        for (int i = 1; i <= maxNum; i++) {
            int bit = 1 << (i - 1);
            if ((mask & bit) == 0) {
                if (currentTotal + i >= target || !canWin(mask | bit, currentTotal + i)) {
                    memo[mask] = true;
                    return true;
                }
            }
        }

        memo[mask] = false;
        return false;
    }
}