import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {
            long start = arrival[i];
            long duration = load[i];

            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.add((int) busy.poll()[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            Integer server = available.ceiling(i % k);
            if (server == null) {
                server = available.first();
            }

            available.remove(server);
            busy.offer(new long[]{start + duration, server});
            count[server]++;
        }

        int maxRequests = 0;
        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}