class Solution {
    public int bestClosingTime(String customers) {
        int totaly=0;
        int totaln=0;
    
        for(int i=0;i<customers.length();i++){
            if(customers.charAt(i)=='Y'){
                totaly++;
            }else if(customers.charAt(i)=='N'){
                totaln++;
            }
        }
        int end=totaln;
        int ans=Integer.MAX_VALUE;
        int hour=Integer.MAX_VALUE;
        int currn=0;
        int penalty=0;
        for(int j=0;j<customers.length();j++){
            penalty=currn*1+totaly*1;
            if(customers.charAt(j)=='Y'){
                totaly--;
            }else if(customers.charAt(j)=='N'){
                currn++;
            }
            if(ans>penalty){
                ans=penalty;
                hour=j;
            }
        }
        if(ans>end){
            return customers.length();
        }
        return hour;
    }
}