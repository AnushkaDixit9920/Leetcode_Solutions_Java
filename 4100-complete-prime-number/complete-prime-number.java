class Solution {
    public boolean completePrime(int num) {
        String s=String.valueOf(num);
        StringBuilder sb= new StringBuilder();
        for (int i=0;i<s.length();i++){
            char c=s.charAt(i);
            sb.append(c);
            int curr=Integer.parseInt(sb.toString());
            if (!isPrime(curr)){
                return false;
            }
        }
        StringBuilder sbb= new StringBuilder();
        for(int j=s.length()-1;j>=0;j--){
            if(j==s.length()-1){
                if(!isPrime(s.charAt(j)-'0')){
                    return false;
                }else{
                    sbb.append(s.charAt(j));
                }
            }
            if(j!=s.length()-1){
                int Num=Integer.parseInt(sbb.toString());
                int digit=s.charAt(j)-'0';
                if(!isPrime(pref(Num,digit))){
                    return false;
                }else{
                    StringBuilder end=new StringBuilder(String.valueOf(pref(Num,digit)));
                    sbb=end;
                }
            }
        }
        return true;
    }
    public boolean isPrime(int n){
        if(n<=1) return false;
        if(n==2) return true;
        if(n%2==0) return false;

        for(int i=3;i*i<=n;i++){
            if(n%i==0) return false;
        }
        return true;
    }
    public int pref(int n, int x){
        int Num=n;
        int digit=x;
        String result=digit+""+Num;
        return Integer.parseInt(result);
    }
    
}