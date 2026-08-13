class Solution {
    static class SegmentTree {
        private final int n;
        private final int[] maxLen;
        private final int[] prefLen;
        private final int[] suffLen;
        private final char[] s;

        public SegmentTree(char[] s) {
            this.s = s;
            this.n = s.length;
            this.maxLen = new int[4 * n];
            this.prefLen = new int[4 * n];
            this.suffLen = new int[4 * n];
            build(1, 0, n - 1);
        }

        private void merge(int node, int l, int mid, int r) {
            int left = node * 2;
            int right = node * 2 + 1;

            maxLen[node] = Math.max(maxLen[left], maxLen[right]);
            prefLen[node] = prefLen[left];
            suffLen[node] = suffLen[right];

            if (s[mid] == s[mid + 1]) {
                if (prefLen[left] == mid - l + 1) {
                    prefLen[node] = prefLen[left] + prefLen[right];
                }
                if (suffLen[right] == r - mid) {
                    suffLen[node] = suffLen[right] + suffLen[left];
                }
                maxLen[node] = Math.max(maxLen[node], suffLen[left] + prefLen[right]);
            }
        }

        private void build(int node, int l, int r) {
            if (l == r) {
                maxLen[node] = 1;
                prefLen[node] = 1;
                suffLen[node] = 1;
                return;
            }
            int mid = l + (r - l) / 2;
            build(node * 2, l, mid);
            build(node * 2 + 1, mid + 1, r);
            merge(node, l, mid, r);
        }

        public void update(int node, int l, int r, int idx, char ch) {
            if (l == r) {
                s[idx] = ch;
                return;
            }
            int mid = l + (r - l) / 2;
            if (idx <= mid) {
                update(node * 2, l, mid, idx, ch);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, ch);
            }
            merge(node, l, mid, r);
        }

        public int getMaxLen() {
            return maxLen[1];
        }
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        char[] chars = s.toCharArray();
        SegmentTree tree = new SegmentTree(chars);
        int k = queryIndices.length;
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            tree.update(1, 0, chars.length - 1, idx, ch);
            result[i] = tree.getMaxLen();
        }

        return result;
    }
}