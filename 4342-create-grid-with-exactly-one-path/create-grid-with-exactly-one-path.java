class Solution {
    public String[] createGrid(int m, int n) {
        StringBuilder[] lines= new StringBuilder[m];
        for(int r=0;r<m;r++){
            lines[r]=new StringBuilder();
            for(int c=0 ;c<n; c++){
                lines[r].append('#');
            }
        }

        for(int c=0;c<n;c++){
            lines[0].setCharAt(c,'.');
        }

        for(int r=0;r<m;r++){
            lines[r].setCharAt(n-1,'.');
        }

        String[]answer= new String[m];
        for(int r=0;r<m;r++){
            answer[r]=lines[r].toString();
        }
        return answer;
    }
}