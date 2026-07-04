
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        char[] s = labels.toCharArray();
        List<int[]>[] adj = new ArrayList[n];
        Arrays.setAll(adj, i -> new ArrayList<>());
        for (int[] e : edges) adj[e[0]].add(new int[]{e[1], e[2]});

        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 1, 0}); 

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], count = curr[1], d = curr[2];

            if (d > dist[u][count]) continue;
            if (u == n - 1) return d;

            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                int nextCount = (s[v] == s[u]) ? count + 1 : 1;
                if (nextCount <= k && d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{v, nextCount, d + w});
                }
            }
        }
        return -1;
    }
}