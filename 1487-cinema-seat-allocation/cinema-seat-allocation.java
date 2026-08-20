import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reservedRows = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            if (col >= 2 && col <= 9) {
                reservedRows.put(row, reservedRows.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        final int LEFT_MASK = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        final int RIGHT_MASK = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
        final int MID_MASK = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        
        int count = 0;
        
        for (int mask : reservedRows.values()) {
            boolean leftFree = (mask & LEFT_MASK) == 0;
            boolean rightFree = (mask & RIGHT_MASK) == 0;
            boolean midFree = (mask & MID_MASK) == 0;
            
            if (leftFree && rightFree) {
                count += 2;
            } else if (leftFree || rightFree || midFree) {
                count += 1;
            }
        }
        
        count += 2 * (n - reservedRows.size());
        
        return count;
    }
}