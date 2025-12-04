class Solution {
    public int countCollisions(String directions) {
        int collisions=0;
        if(directions.length()==1){
            return 0;
        }
        char[] arr= directions.toCharArray();
        int i=0;
        while(i<arr.length){
            char c=arr[i];
            if(c=='R'){
                if(i==0){
                    if(arr[i+1]=='R'){
                        i++;
                    }else if(arr[i+1]=='L'){
                        collisions+=2;
                        arr[i]='S';
                        arr[i+1]='S';
                        i++;
                    }else if(arr[i+1]=='S'){
                        collisions+=1;
                        arr[i]='S';
                        i=Math.max(0,i-1);
                  
                    }
                }else if(i==arr.length-1){
                    i++;
                }else if( i>0 && i<arr.length-1){
                    if(arr[i+1]=='R'){
                        i++;
                    }else if(arr[i+1]=='L'){
                        collisions+=2;
                        arr[i]='S';
                        arr[i+1]='S';
                        i=Math.max(0,i-1);
                        
                    }else if(arr[i+1]=='S'){
                        collisions+=1;
                        arr[i]='S';
                        i=Math.max(0,i-1);
                    
                    }
                }
            }else if(c=='S'){
                if(i==0){
                    if(arr[i+1]=='L'){
                        collisions+=1;
                        arr[i+1]='S';
                        i++;
                    }else if(arr[i+1]=='R'){
                        i++;
                    }else if(arr[i+1]=='S'){
                        i++;
                    }
                }else if(i==arr.length-1){
                    if(arr[i-1]=='R'){
                        collisions+=1;
                        arr[i-1]='S';
                        i=Math.max(0,i-1);
                       
                    }else if(arr[i-1]=='L' || arr[i-1]=='S'){
                        i++;
                    }
                }else if(i>0 && i<arr.length-1){
                    if(arr[i+1]=='L'){
                        collisions+=1;
                        arr[i+1]='S';
                        i++;
                    }else if(arr[i-1]=='R'){
                        collisions+=1;
                        arr[i-1]='S';
                        i=Math.max(0,i-1);
                       
                    }else if(arr[i+1]=='R'){
                        i++;
                    }else if(arr[i+1]=='S'){
                        i++;
                    }else if(arr[i-1]=='L'){
                        i++;
                    }else if(arr[i-1]=='S'){
                        i++;
                    }
                }
            }else if(c=='L'){
                if(i==0){
                    i++;
                }else if(i==arr.length-1){
                    if(arr[i-1]=='L'){
                        i++;
                    }else if(arr[i-1]=='R'){
                        collisions+=2;
                        arr[i-1]='S';
                        arr[i]='S';
                        i=Math.max(0,i-1);
                    
                    }else if (arr[i-1]=='S'){
                        collisions+=1;
                        arr[i]='S';
                        i=Math.max(0,i-1);
                   
                    }
                }else if(i>0 && i<arr.length-1){
                    if(arr[i-1]=='R'){
                        collisions+=2;
                        arr[i-1]='S';
                        arr[i]='S';
                        i=Math.max(0,i-1);
                      
                    }else if(arr[i-1]=='L'){
                        i++;
                    }else if(arr[i-1]=='S'){
                        collisions+=1;
                        arr[i]='S';
                        i=Math.max(0,i-1);

                    }
                }
            }
        }
        return collisions;
    }
}