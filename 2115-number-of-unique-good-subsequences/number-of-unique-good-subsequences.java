class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        int MOD = 1_000_000_007;
        int ends0 = 0;
        int ends1 = 0;
        int hasZero = 0;
        for (int i = 0; i < binary.length(); i++) {
            char ch = binary.charAt(i);
            if (ch == '1') {
                ends1 = (ends0 + ends1 + 1) % MOD;
            } else {
                ends0 = (ends0 + ends1) % MOD;
                hasZero = 1;
            }
        }
        return (ends0 + ends1 + hasZero) % MOD;
    }
}