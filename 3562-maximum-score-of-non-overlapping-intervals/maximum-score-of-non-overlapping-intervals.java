import java.util.*;

class Solution {
    static class Interval {
        int l, r, w, id;

        Interval(int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }

    static class State {
        long weight;
        int[] indices;

        State(long weight, int[] indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> curr = intervals.get(i);
            arr[i] = new Interval(curr.get(0), curr.get(1), curr.get(2), i);
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a.r, b.r));

        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new int[0]);
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = arr[i - 1];
            int p = lowerBound(arr, curr.l);

            for (int k = 1; k <= 4; k++) {
                State best = dp[i - 1][k];

                if (dp[p][k - 1] != null) {
                    long candWeight = dp[p][k - 1].weight + curr.w;
                    int[] candIndices = addAndSort(dp[p][k - 1].indices, curr.id);
                    State cand = new State(candWeight, candIndices);

                    if (isBetter(cand, best)) {
                        best = cand;
                    }
                }

                dp[i][k] = best;
            }
        }

        State bestResult = null;
        for (int k = 1; k <= 4; k++) {
            if (isBetter(dp[n][k], bestResult)) {
                bestResult = dp[n][k];
            }
        }

        return bestResult != null ? bestResult.indices : new int[0];
    }

    private int lowerBound(Interval[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (arr[mid].r >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private int[] addAndSort(int[] prev, int val) {
        int[] next = new int[prev.length + 1];
        System.arraycopy(prev, 0, next, 0, prev.length);
        next[prev.length] = val;
        Arrays.sort(next);
        return next;
    }

    private boolean isBetter(State a, State b) {
        if (a == null) return false;
        if (b == null) return true;
        if (a.weight != b.weight) {
            return a.weight > b.weight;
        }
        return isLexicographicallySmaller(a.indices, b.indices);
    }

    private boolean isLexicographicallySmaller(int[] a, int[] b) {
        int minLen = Math.min(a.length, b.length);
        for (int i = 0; i < minLen; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}