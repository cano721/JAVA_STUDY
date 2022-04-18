class Solution {
    public int solution(int[][] board, int[][] skill) {
        int rlen = board.length;
        int clen = board[0].length;
        int cnt =0;
        long[][] temp = new long[rlen][clen];
        
        for(int[] s : skill){
            int type = s[0], r1 = s[1], c1=s[2],r2=s[3],c2 = s[4], degree = s[5];
            
            long val = (type==1) ? -degree : degree;
            
            temp[r1][c1]+= val;
            if(c2< clen -1) temp[r1][c2+1] -= val;
            if(r2< rlen -1) temp[r2+1][c1] -= val;
            if(r2<rlen-1 && c2<clen -1) temp[r2+1][c2+1] += val;
    }
            for(int i=0; i<rlen; i++){
                for(int j=1; j<clen; j++){
                    temp[i][j] += temp[i][j-1];
                }
            }
            
            for(int i=1; i<rlen; i++){
                for(int j=0; j<clen; j++){
                    temp[i][j] += temp[i-1][j];
                }
            }
            
            
            for(int i=0; i<rlen; i++){
                for(int j=0; j<clen; j++){
                    temp[i][j]+=board[i][j];
                    if(temp[i][j]>0) cnt ++;
                }
            }
            
        
        
        int answer = cnt;
        return answer;
    }
}