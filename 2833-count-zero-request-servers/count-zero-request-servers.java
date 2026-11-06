import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int qLen = queries.length;
        Integer[] order = new Integer[qLen];
        for (int i = 0; i < qLen; i++) {
            order[i] = i;
        }
        Arrays.sort(order, Comparator.comparingInt(i -> queries[i]));

        int[] ans = new int[qLen];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;

        int left = 0;
        int right = 0;
        int m = logs.length;

        for (int idx : order) {
            int qTime = queries[idx];
            int startTime = qTime - x;
            int endTime = qTime;

            while (right < m && logs[right][1] <= endTime) {
                int sId = logs[right][0];
                if (serverCount[sId] == 0) {
                    activeServers++;
                }
                serverCount[sId]++;
                right++;
            }

            while (left < m && logs[left][1] < startTime) {
                int sId = logs[left][0];
                serverCount[sId]--;
                if (serverCount[sId] == 0) {
                    activeServers--;
                }
                left++;
            }

            ans[idx] = n - activeServers;
        }

        return ans;
    }
}