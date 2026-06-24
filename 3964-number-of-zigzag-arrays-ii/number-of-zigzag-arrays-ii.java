class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
       
        int sz = m * 2;
        long[][] mat = new long[sz][sz];
        for (int v = 0; v < m; v++) {
           
            for (int u = 0; u < v; u++) {
                mat[u * 2 + 1][v * 2 + 0] = 1;
            }
            
            for (int u = v + 1; u < m; u++) {
                mat[u * 2 + 0][v * 2 + 1] = 1; 
            }
        }

        
        long[] vec = new long[sz];
        for (int prev = 0; prev < m; prev++) {
            for (int curr = 0; curr < m; curr++) {
                if (prev < curr) vec[curr * 2 + 0]++;  
                else if (prev > curr) vec[curr * 2 + 1]++; 
            }
        }

        
        long[][] result = matPow(mat, n - 2, sz);
        long[] finalVec = matVecMul(result, vec, sz);

        long ans = 0;
        for (long x : finalVec) ans = (ans + x) % MOD;
        return (int) ans;
    }

    long[][] matMul(long[][] A, long[][] B, int sz) {
        long[][] C = new long[sz][sz];
        for (int i = 0; i < sz; i++)
            for (int k = 0; k < sz; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < sz; j++)
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
            }
        return C;
    }

    long[][] matPow(long[][] M, int p, int sz) {
        long[][] result = new long[sz][sz];
        for (int i = 0; i < sz; i++) result[i][i] = 1; // identity
        while (p > 0) {
            if ((p & 1) == 1) result = matMul(result, M, sz);
            M = matMul(M, M, sz);
            p >>= 1;
        }
        return result;
    }

    long[] matVecMul(long[][] A, long[] v, int sz) {
        long[] out = new long[sz];
        for (int i = 0; i < sz; i++)
            for (int j = 0; j < sz; j++)
                out[i] = (out[i] + A[i][j] * v[j]) % MOD;
        return out;
    }
}