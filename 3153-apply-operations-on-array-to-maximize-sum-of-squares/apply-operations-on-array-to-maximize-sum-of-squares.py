class Solution(object):
    def maxSum(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        MOD=10**9+7
        count=[0]*31
        for x in nums:
            b=0
            while x:
                if x & 1 :
                    count[b]+=1
                x>>=1
                b+=1
        ans=0
        for _ in range(k):
            val=0
            for b in range(31):
                if count[b]>0:
                    val|= (1<<b)
                    count[b]-=1
            ans=( ans + val*val)%MOD
        return ans
        