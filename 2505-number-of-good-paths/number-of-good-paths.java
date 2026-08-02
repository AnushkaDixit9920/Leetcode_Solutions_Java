
class Solution {
    private int[] parent;
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }
    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n = vals.length;
        parent = new int[n];
        int[] maxVal = new int[n];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            maxVal[i] = vals[i];
            count[i] = 1;
        }

        Arrays.sort(edges, (a, b) -> {
            int maxA = Math.max(vals[a[0]], vals[a[1]]);
            int maxB = Math.max(vals[b[0]], vals[b[1]]);
            return Integer.compare(maxA, maxB);
        });

        int ans = n;

        for (int[] edge : edges) {
            int rootU = find(edge[0]);
            int rootV = find(edge[1]);

            if (rootU != rootV) {
                if (maxVal[rootU] == maxVal[rootV]) {
                    ans += count[rootU] * count[rootV];
                    parent[rootV] = rootU;
                    count[rootU] += count[rootV];
                } else if (maxVal[rootU] > maxVal[rootV]) {
                    parent[rootV] = rootU;
                } else {
                    parent[rootU] = rootV;
                }
            }
        }

        return ans;
    }
}