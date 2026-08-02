class Solution {
    vector<signed char>memo;
    int maxChoosable;
    bool canWin(int used, int desiredTotal) {
        if(memo[used]!=0) return memo[used]==1;
        for(int i=1;i<=maxChoosable;i++){
            int bit=1<<(i-1);
            if(!(used &bit)){
                if(i>=desiredTotal || !canWin(used | bit, desiredTotal-i)){
                    memo[used]=1;
                    return true;
                }
            }
        }
        memo[used]=-1;
        return false;
    }
    public:
    bool canIWin(int maxChoosableInteger, int desiredTotal){
        if(desiredTotal<=0) return true;
        int sum=maxChoosableInteger*(maxChoosableInteger+1)/2;
        if(sum<desiredTotal) return false;
        maxChoosable=maxChoosableInteger;
        memo.assign(1<<maxChoosableInteger,0);
        return canWin(0,desiredTotal);
    }
};