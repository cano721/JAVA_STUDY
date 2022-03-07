class Solution {
    public int solution(int m, int n, String[] board) {

        char[][] cboard = new char[m][n];
        for(int i=0; i<board.length; i++){
            cboard[i] = board[i].toCharArray();
        }

        int cnt;
        int answer = 0;

        while((cnt = checkAndRemove(cboard))>0) {
            answer+= cnt;
        }
        return answer;
    }
    public int checkAndRemove(char[][] cboard){
        int m = cboard.length;
        int n = cboard[0].length;
        int cnt =0;
        boolean[][] checked = new boolean[m][n];

        for(int i=0; i< m-1; i++){
            for(int j=0; j<n-1;j++){
                char current = cboard[i][j];
                if(current == 'X') continue;
                if(cboard[i][j+1]==current &&cboard[i+1][j]==current &&cboard[i+1][j+1]==current){
                    checked[i][j] = true;
                    checked[i][j+1] = true;
                    checked[i+1][j] = true;
                    checked[i+1][j+1] = true;
                }
            }
        }

       for(int i=0; i<m; i++){
           for(int j=0; j<n;j++){
               if(checked[i][j]) {
                   cboard[i][j] ='X';
                   cnt++;
               }
           }
       }

       moveBlock(cboard, m ,n); 
       return cnt; 
    }

    public void moveBlock(char[][] cboard,int m, int n){
        for(int j=0; j<n;j++){
            for(int i=m-1;i>=0;i--){
             if(cboard[i][j]!='X'){
                 int movPos = m-1;
                 for(int k= i+1; k<m; k++){
                     if(cboard[k][j]!='X'){
                         movPos = k-1;
                         break;
                     }
                 }
                 cboard[movPos][j] =cboard[i][j];
                 if(movPos != i)cboard[i][j] = 'X';
             }
        }
        }

    }
}