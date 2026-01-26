class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n=arr.length;
        Arrays.sort(arr);
        int mindiff=Integer.MAX_VALUE;
        for(int i=0;i<=n-2;i++){
            int j=i+1;
            mindiff=Math.min(arr[j]-arr[i],mindiff);
        }
        List<List<Integer>>ll= new ArrayList<>();
        for(int i=0;i<=n-2;i++){
            int j=i+1;
            if((arr[j]-arr[i])==mindiff){
                ArrayList<Integer>LL= new ArrayList<>();
                LL.add(arr[i]);
                LL.add(arr[j]);
                ll.add(LL);
            }
        }
        return ll;
    }
}