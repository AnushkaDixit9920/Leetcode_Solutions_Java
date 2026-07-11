

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] degrees = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degrees[u]++;
            degrees[v]++;
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);

                int V = component.size();
                boolean isComplete = true;
                for (int node : component) {
                    if (degrees[node] != V - 1) {
                        isComplete = false;
                        break;
                    }
                }
                
                if (isComplete) {
                    completeComponents++;
                }
            }
        }

        return completeComponents;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }
}