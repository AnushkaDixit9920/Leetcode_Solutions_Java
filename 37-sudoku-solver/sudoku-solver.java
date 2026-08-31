class Solution {
    private boolean[][]rows=new boolean[9][10];
    private boolean[][] cols=new boolean[9][10];
    private boolean[][] boxes= new boolean[9][10];
    public void solveSudoku(char[][] board) {
        for(int r=0;r<9;r++){
            for(int c=0; c<9;c++){
                if(board[r][c]!='.'){
                    int num=board[r][c]-'0';
                    int boxIdx=(r/3)*3+(c/3);
                    rows[r][num]=true;
                    cols[c][num]=true;
                    boxes[boxIdx][num]=true;
                }
            }
        }
        backtrack(board,0,0);
    }
    private boolean backtrack(char[][]board, int r, int c){
        if(r==9){
            return true;
        }
        if(c==9){
            return backtrack(board,r+1,0);
        }
        if(board[r][c]!= '.'){
            return backtrack(board,r,c+1);
        }

        int boxIdx=(r/3)*3+(c/3);

        for(int num=1;num<=9;num++){
            if(!rows[r][num] && !cols[c][num] && !boxes[boxIdx][num]){
                board[r][c]=(char)('0'+num);
                rows[r][num]=true;
                cols[c][num]=true;
                boxes[boxIdx][num]=true;
                if(backtrack(board,r,c+1)){
                    return true;
                }
                board[r][c]='.';
                rows[r][num]=false;
                cols[c][num]=false;
                boxes[boxIdx][num]=false;
            }
        }
        return false;
    }
}