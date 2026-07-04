
class Solution {

    static class Edge {
        int to, wt;

        Edge(int to, int wt) {
            this.to = to;
            this.wt = wt;
        }
    }

    static class State {
        int node, cnt;
        long dist;

        State(int node, int cnt, long dist) {
            this.node = node;
            this.cnt = cnt;
            this.dist = dist;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[1], e[2]));
        }

        long INF = Long.MAX_VALUE;
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist != dist[cur.node][cur.cnt]) {
                continue;
            }

            if (cur.node == n - 1) {
                return (int) cur.dist;
            }

            for (Edge e : graph[cur.node]) {

                int newCnt;

                if (labels.charAt(e.to) == labels.charAt(cur.node)) {
                    newCnt = cur.cnt + 1;
                    if (newCnt > k) {
                        continue;
                    }
                } else {
                    newCnt = 1;
                }

                long newDist = cur.dist + e.wt;

                if (newDist < dist[e.to][newCnt]) {
                    dist[e.to][newCnt] = newDist;
                    pq.offer(new State(e.to, newCnt, newDist));
                }
            }
        }

        return -1;
    }
}