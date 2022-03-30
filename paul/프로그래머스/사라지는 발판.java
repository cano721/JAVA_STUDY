// A -> B, B -> A
// 
import java.util.*;
class Solution {
    
    static class Result{
        boolean flag =false;
        int cnt =0;
        public Result(boolean ff, int c){
            flag =ff;
            cnt =c;
        }
    }

    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static int n, m;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        Result answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1], board);
        return answer.cnt;
    }
    
    static boolean isValid(int y, int x){
        if(y < 0 || y>=n || x <0 || x>=m ) return false;
        else return true;
    }
    
    static boolean canMove(int y, int x, int[][] board){
        for(int i =0; i<4; i++){
            int yy = y + dy[i], xx = x+dx[i];
            if( isValid(yy,xx) && board[yy][xx] == 1) return true;
        }
        return false;
    }
    
    //현재 턴 y,x 좌표 , 다음 턴 y,x 좌표.
    static Result dfs(int ay, int ax, int by, int bx, int[][] board){
        // 움직일 수 없거나 발판이 없으면 return.
        if(!canMove(ay, ax, board) || board[ay][ax] == 0) return new Result(false, 0);
        int minVal = 1000000, maxVal = 0;
        Result now = new Result(false, 0);
        for(int i=0; i<4; i++){
            int yy = ay+dy[i], xx = ax+dx[i];
            
            if(!isValid(yy,xx) || board[yy][xx] == 0) continue;
            
            board[yy][xx] = 0;
            Result nextTurn = dfs(by, bx, yy, xx, board);
            board[yy][xx] = 1;
            
            // 상대가 다음턴에 진
            if(!nextTurn.flag){
                //최선을 다해 쫓아간다.
                minVal = Math.min(minVal, nextTurn.cnt);
                now.flag = true;
            }else if(!now.flag){
                //지는 경우
                maxVal = Math.max(maxVal, nextTurn.cnt);
            }
        }
        now.cnt = now.flag? minVal +1 : maxVal +1;
        return now;
    }
    
    
}