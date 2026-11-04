import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    private static final int OFFSET = 1000000;
    private int[] delayed = new int[2000005];
    private PriorityQueue<Long> leftHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Long> rightHeap = new PriorityQueue<>();
    private long sumLeft = 0, sumRight = 0;
    private int leftSize = 0, rightSize = 0;

    private void pruneLeft() {
        while (!leftHeap.isEmpty() && delayed[(int) (leftHeap.peek() + OFFSET)] > 0) {
            delayed[(int) (leftHeap.peek() + OFFSET)]--;
            leftHeap.poll();
        }
    }

    private void pruneRight() {
        while (!rightHeap.isEmpty() && delayed[(int) (rightHeap.peek() + OFFSET)] > 0) {
            delayed[(int) (rightHeap.peek() + OFFSET)]--;
            rightHeap.poll();
        }
    }

    private void balance() {
        int target = (leftSize + rightSize + 1) / 2;
        while (leftSize > target) {
            pruneLeft();
            long val = leftHeap.poll();
            sumLeft -= val;
            leftSize--;
            rightHeap.offer(val);
            sumRight += val;
            rightSize++;
        }
        while (leftSize < target) {
            pruneRight();
            long val = rightHeap.poll();
            sumRight -= val;
            rightSize--;
            leftHeap.offer(val);
            sumLeft += val;
            leftSize++;
        }
    }

    private void add(long num) {
        pruneLeft();
        if (leftHeap.isEmpty() || num <= leftHeap.peek()) {
            leftHeap.offer(num);
            sumLeft += num;
            leftSize++;
        } else {
            rightHeap.offer(num);
            sumRight += num;
            rightSize++;
        }
        balance();
    }

    private void remove(long num) {
        pruneLeft();
        pruneRight();
        if (num <= leftHeap.peek()) {
            leftSize--;
            sumLeft -= num;
        } else {
            rightSize--;
            sumRight -= num;
        }
        delayed[(int) (num + OFFSET)]++;
        pruneLeft();
        pruneRight();
        balance();
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        pruneLeft();
        long median = leftHeap.peek();
        cost[0] = (long) (leftSize - rightSize) * median + sumRight - sumLeft;

        for (int i = x; i < n; i++) {
            add(nums[i]);
            remove(nums[i - x]);
            pruneLeft();
            median = leftHeap.peek();
            cost[i - x + 1] = (long) (leftSize - rightSize) * median + sumRight - sumLeft;
        }

        long INF = 1_000_000_000_000_000_000L;
        long[] prevDp = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] currDp = new long[n + 1];
            for (int i = 0; i <= n; i++) {
                currDp[i] = INF;
            }
            for (int i = 1; i <= n; i++) {
                currDp[i] = currDp[i - 1];
                if (i >= x && prevDp[i - x] != INF) {
                    currDp[i] = Math.min(currDp[i], prevDp[i - x] + cost[i - x]);
                }
            }
            prevDp = currDp;
        }

        return prevDp[n];
    }
}