import java.util.*;
class Solution {
    class Job {
        int start, end, profit;
        Job(int s, int e, int p) {
            start = s;
            end = e;
            profit = p;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, (a, b) -> a.start - b.start);

        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {

            int next = findNext(jobs, jobs[i].end);

            int take = jobs[i].profit + dp[next];
            int skip = dp[i + 1];

            dp[i] = Math.max(take, skip);
        }

        return dp[0];
    }
    private int findNext(Job[] jobs, int endTime) {
        int l = 0, r = jobs.length;
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (jobs[mid].start < endTime)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}