class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int allMask = (1 << n) - 1;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];

        for (int i = 0; i < n; i++) {
            int mask = 1 << i;
            q.add(new int[]{i, mask, 0});
            visited[i][mask] = true;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], mask = cur[1], dist = cur[2];

            if (mask == allMask) return dist;

            for (int next : graph[node]) {
                int nextMask = mask | (1 << next);
                if (!visited[next][nextMask]) {
                    visited[next][nextMask] = true;
                    q.add(new int[]{next, nextMask, dist + 1});
                }
            }
        }
        return -1;
    }
}
