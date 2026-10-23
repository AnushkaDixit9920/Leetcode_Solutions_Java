class Solution(object):
    def onesMinusZeros(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: List[List[int]]
        """
        m,n=len(grid), len(grid[0])
        row_ones=[sum(row) for row in grid]
        col_ones=[sum(col) for col in zip(*grid)]
        return [[2*(row_ones[i]+col_ones[j])-m-n for j in range(n)] for i in range (m)]

        