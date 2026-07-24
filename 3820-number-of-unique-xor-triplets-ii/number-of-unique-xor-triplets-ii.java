import java.util.BitSet;

public class Solution {
    private static final int MAX_XOR_RANGE = 2048;

    public int uniqueXorTriplets(int[] nums) {
        int[] uniqueNums = extractUniqueElements(nums);
        BitSet pairwiseXors = computePairwiseXors(uniqueNums);
        BitSet tripletXors = computeTripletXors(pairwiseXors, uniqueNums);
        
        return tripletXors.cardinality();
    }

    private int[] extractUniqueElements(int[] nums) {
        BitSet seen = new BitSet(MAX_XOR_RANGE);
        for (int num : nums) {
            seen.set(num);
        }

        int[] uniqueNums = new int[seen.cardinality()];
        int idx = 0;
        for (int val = seen.nextSetBit(0); val >= 0; val = seen.nextSetBit(val + 1)) {
            uniqueNums[idx++] = val;
        }
        return uniqueNums;
    }

    private BitSet computePairwiseXors(int[] uniqueNums) {
        BitSet pairwiseXors = new BitSet(MAX_XOR_RANGE);
        int n = uniqueNums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pairwiseXors.set(uniqueNums[i] ^ uniqueNums[j]);
            }
        }
        return pairwiseXors;
    }

    private BitSet computeTripletXors(BitSet pairwiseXors, int[] uniqueNums) {
        BitSet tripletXors = new BitSet(MAX_XOR_RANGE);

        for (int p = pairwiseXors.nextSetBit(0); p >= 0; p = pairwiseXors.nextSetBit(p + 1)) {
            for (int w : uniqueNums) {
                tripletXors.set(p ^ w);
            }
        }
        return tripletXors;
    }
}