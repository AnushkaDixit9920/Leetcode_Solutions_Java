class Solution(object):
    def minOrAfterOperations(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: int
        """
        ans=0
        mask=0
        n=len(nums)
        for b in range (29,-1,-1):
            test_mask=mask | (1<<b)
            cnt=0
            cur=(1<<30) -1

            for x in nums:
                cur &=x
                if(cur & test_mask)==0:
                    cnt+=1
                    cur=(1<<30)-1
            if n-cnt<=k:
                mask=test_mask
            else:
                ans|=(1<<b)
        return ans
        