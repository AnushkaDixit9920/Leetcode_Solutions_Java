class Solution {
    static class Node {
        int prod;
        int[] freq = new int[5];
    }
    private int n, k;
    private Node[] tree;
    private int curProd;
    private int ans;
    private Node merge(Node left, Node right) {
        Node res = new Node();
        res.prod = (left.prod * right.prod) % k;
        for (int i = 0; i < k; ++i) {
            res.freq[i] = left.freq[i];
        }
        for (int u = 0; u < k; ++u) {
            if (right.freq[u] > 0) {
                int combinedRem = (left.prod * u) % k;
                res.freq[combinedRem] += right.freq[u];
            }
        }
        return res;
    }
    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int val = nums[l] % k;
            tree[node].prod = val;
            tree[node].freq[val] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }
    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            for (int i = 0; i < k; ++i) {
                tree[node].freq[i] = 0;
            }
            int rem = val % k;
            tree[node].prod = rem;
            tree[node].freq[rem] = 1;
            return;
        }
        int mid = l + (r - l) / 2;
        if (idx <= mid) {
            update(2 * node, l, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, r, idx, val);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }
    private void queryRange(int node, int l, int r, int ql, int qr, int xi) {
        if (ql <= l && r <= qr) {
            for (int u = 0; u < k; ++u) {
                if ((curProd * u) % k == xi) {
                    ans += tree[node].freq[u];
                }
            }
            curProd = (curProd * tree[node].prod) % k;
            return;
        }
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            queryRange(2 * node, l, mid, ql, qr, xi);
        }
        if (qr > mid) {
            queryRange(2 * node + 1, mid + 1, r, ql, qr, xi);
        }
    }
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; ++i) {
            this.tree[i] = new Node();
        }
        build(1, 0, n - 1, nums);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            update(1, 0, n - 1, idx, val);
            ans = 0;
            curProd = 1;
            queryRange(1, 0, n - 1, start, n - 1, x);
            result[i] = ans;
        }
        return result;
    }
}