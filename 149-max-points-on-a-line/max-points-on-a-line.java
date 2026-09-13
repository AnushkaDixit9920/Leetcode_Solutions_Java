import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int maxPoints = 2;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopes = new HashMap<>();
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - x1;
                int dy = points[j][1] - y1;
                if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                } else {
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }
                    int g = gcd(Math.abs(dx), Math.abs(dy));
                    dx /= g;
                    dy /= g;
                }
                String slope = dx + "/" + dy;
                int count = slopes.getOrDefault(slope, 0) + 1;
                slopes.put(slope, count);
                maxPoints = Math.max(maxPoints, count + 1);
            }
        }
        return maxPoints;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}