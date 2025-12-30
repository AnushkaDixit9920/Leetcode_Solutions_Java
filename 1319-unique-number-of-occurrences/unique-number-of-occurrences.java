class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer>mp= new HashMap<>();
        for(int x:arr){
            mp.put(x,mp.getOrDefault(x,0)+1);
        }
        HashSet<Integer>st= new HashSet<>();
        for(var e:mp.entrySet()){
            if(st.contains(e.getValue())){
                return false;
            }else{
                st.add(e.getValue());
            }

        }
        return true;
    }
}