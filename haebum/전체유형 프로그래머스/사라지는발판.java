/**
    1. dfs 완탐
    
**/
class Solution {
    
    public int[] dirX = {0,0,1,-1};
    public int[] dirY = {1,-1,0,0};
    
    public int answer;
    public int max = 10_000_000;
    
    // 승패여부와 움직인횟수 저장 클래스
    public class WF{
        boolean win;
        int cnt;
        
        public WF(boolean win, int cnt){
            this.win = win;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        WF result = dfs(board,aloc,bloc,1,0);
        
        return result.cnt;
    }
    
    
    
    public WF dfs(int[][] board,int[] aloc, int[] bloc, int turn, int move){
        
        int ax = aloc[0];
        int ay = aloc[1];
        
        int bx = bloc[0];
        int by = bloc[1];
        
        // a턴이면서 a가 졌거나 b턴이면서 b가 지면,패배사
        if((turn > 0 && board[ax][ay] == 0) || (turn < 0 && board[bx][by] == 0)){
            return new WF(false,move);
        }
        
        int win = max;
        int lose = 0;
        
        
        // 4방향 체크
        for(int i = 0; i < 4; i++){
            // a차례
            if(turn > 0){
                int nax = ax + dirX[i];
                int nay = ay + dirY[i];
                
                // 맵 벗어났으면
                if(0 > nax || nax >= board.length || 0 > nay || nay >= board[0].length){
                    continue;
                }
                // 발판 없으면
                if(board[nax][nay] == 0){
                    continue;
                }
                
                board[ax][ay] = 0;
                // 다음순서가 졌을경우 이번순서는 이긴 것
                WF b = dfs(board,new int[] {nax,nay},bloc,-turn,move+1);
                if(b.win == false){
                    win = Math.min(win,b.cnt);
                }else{
                    lose = Math.max(lose,b.cnt);
                }
                board[ax][ay] = 1;
            // b차례
            }else{
                int nbx = bx + dirX[i];
                int nby = by + dirY[i];
                
                // 맵 벗어났으면
                if(0 > nbx || nbx >= board.length || 0 > nby || nby >= board[0].length){
                    continue;
                }
                // 발판 없으면
                if(board[nbx][nby] == 0){
                    continue;
                }
                
                board[bx][by] = 0;
                WF a = dfs(board,aloc,new int[] {nbx,nby},-turn,move+1);
                if(a.win == false){
                    win = Math.min(win,a.cnt);
                }else{
                    lose = Math.max(lose,a.cnt);
                }
                board[bx][by] = 1;
            }
        }
        
        //더 움직일 수 없을 때 현재 움직인 횟수 반환
        if(win==max && lose == 0){
            return new WF(false,move);
        // 이겼을 때 최저값 반환
        }else if(win != max){
            return new WF(true,win);
        // 졌을 때 최대값 반환
        }else{
            return new WF(false,lose);
        }
    }
}