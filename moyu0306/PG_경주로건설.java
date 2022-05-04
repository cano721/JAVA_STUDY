import java.util.*;
class Solution {
    int[][][] DP;
    Queue<int[]> q = new LinkedList<>();
    int[] dy = new int[]{-1,0,1,0};
    int[] dx = new int[]{0,-1,0,1};
public int solution(int[][] board){
    int rlen = board.length;
    int clen = board[0].length;
    int min = Integer.MAX_VALUE;
    DP = new int[rlen][clen][4];
        for(int i=0; i<rlen; i++){
            for(int j=0; j<clen ;j++){
                for(int k= 0; k<4; k++){
                    DP[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        DP[0][0][0] = 0;
        DP[0][0][1] = 0;
        DP[0][0][2] = 0;
        DP[0][0][3] = 0;
        q.offer(new int[]{0,0,2});
        q.offer(new int[]{0,0,3});
        while(!q.isEmpty()){
            int[] pos = q.poll();
            for(int i=0; i<4; i++){
                int posY = pos[0] + dy[i];
                int posX = pos[1] + dx[i];
                if(posY<0||posX<0||posY>=rlen||posX>=clen||board[posY][posX]==1) continue;
                if((i+pos[2])%2 == 1){
                    if(DP[posY][posX][i]>DP[pos[0]][pos[1]][pos[2]] + 600){
                        DP[posY][posX][i] =DP[pos[0]][pos[1]][pos[2]] + 600;
                        q.offer(new int[]{posY,posX,i});
                    }
                }else if(i == pos[2]) {
                    if (DP[posY][posX][i] > DP[pos[0]][pos[1]][pos[2]] + 100) {
                        DP[posY][posX][i] = DP[pos[0]][pos[1]][pos[2]] + 100;
                        q.offer(new int[]{posY, posX, i});
                    }
                }
            }
        }

        for(int i=0; i<4;i++){
            min = Integer.min(DP[rlen-1][clen-1][i],min);
        }
            return min;
}


}