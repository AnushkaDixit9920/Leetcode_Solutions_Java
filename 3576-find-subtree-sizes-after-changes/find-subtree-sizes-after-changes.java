import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        int[] newParent = parent.clone();
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int[] order = new int[n];
        int idx = 0;

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0, -1});

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            int u = cur[0];
            int exiting = cur[1];
            int prev = cur[2];
            int c = s.charAt(u) - 'a';

            if (exiting == 1) {
                lastSeen[c] = prev;
                continue;
            }

            order[idx++] = u;
            if (u != 0 && lastSeen[c] != -1) {
                newParent[u] = lastSeen[c];
            }

            stack.push(new int[]{u, 1, lastSeen[c]});
            lastSeen[c] = u;

            for (int v : adj.get(u)) {
                stack.push(new int[]{v, 0, -1});
            }
        }

        int[] answer = new int[n];
        Arrays.fill(answer, 1);
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            if (u != 0) {
                answer[newParent[u]] += answer[u];
            }
        }

        return answer;
    }
}