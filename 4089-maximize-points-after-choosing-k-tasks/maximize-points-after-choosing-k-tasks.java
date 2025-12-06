class Solution {
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        int n=technique1.length;
        int[][] Arr= new int[n][3];
        for(int j=0;j<Arr.length;j++){
            Arr[j][0]=technique1[j]-technique2[j];
            Arr[j][1]=technique1[j];
            Arr[j][2]=technique2[j];
        }
        Arrays.sort(Arr, (a,b)-> b[0]-a[0]);
        long ans=0;
        for(int i=0;i<k;i++){
            ans+=Arr[i][1];
        }
        for(int h=k;h<n;h++){
            ans+=Math.max(Arr[h][1],Arr[h][2]);
        }
        return ans;
    }
}