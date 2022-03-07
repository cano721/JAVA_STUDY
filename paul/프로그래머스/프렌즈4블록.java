import java.util.*;
import java.io.*;
class Solution {
    static int[][] moves = {{0,0}, {1,0}, {0,1}, {1,1} };
    static class Pair{
        int y, x;
        public Pair(int yy, int xx) {
            y =yy;
            x =xx;
        }
    }
    public int solution(int m, int n, String[] b) {
        
        StringBuilder[] board = new StringBuilder[b.length];
        for(int i =0; i< b.length; i++){
            board[i] = new StringBuilder(b[i]);
        }
        
        int answer = 0;
        
        while(true){
            int cnt = 0;
            List<Pair> list = new ArrayList<>();
            for(int i = 0; i< m; i++){
                for(int j =0; j<n; j++){
                    if(find(i, j, m, n, board)){
                        list.add(new Pair(i,j));
                    }
                }
            }
            
            if(list.size() == 0 ) break;
            
            for(Pair pair : list){
                cnt += destroy(pair.y, pair.x, board);
            }
        
            answer += cnt;
            moveDown(m, n, board);
            
            showBoard(board);
        }
        
        return answer;
    }
    
    static boolean find(int y, int x, int m, int n, StringBuilder[] board){
        char k = board[y].charAt(x);
        if(k == '0') return false;
        for(int i =1; i<4; i++){
            int yy = y + moves[i][0];
            int xx = x + moves[i][1];
            if( yy < 0 || xx < 0 || yy >=m || xx>=n) return false;
            if( k != board[yy].charAt(xx)) return false;
        }
        return true;
    }
    
    static int destroy(int y, int x, StringBuilder[] board){
        int cnt = 0;
        for(int i =0; i<4; i++){
            int yy = y + moves[i][0], xx = x+moves[i][1];
            int k = board[yy].charAt(xx);
            if( k != '0'){
                cnt++;
                board[yy].setCharAt(xx, '0');
            }
        }
        return cnt;
    }
    
    static void moveDown(int m, int n, StringBuilder[] board){
        for(int x =0; x<n; x++){
            for(int y = m-2; y >= 0; y--){
                // 밑에 바닥일 때 까지 내리기
                char tmp = board[y].charAt(x);
                if(tmp == '0') continue;
                int loc = y+1;
                
                while(loc < m && board[loc].charAt(x) == '0'){
                    loc++;
                }
                
                board[y].setCharAt(x, '0');
                board[loc-1].setCharAt(x, tmp);
                
            }
        }
    }
    
    static void showBoard(StringBuilder[] board){
        for(int i =0; i< board.length; i++){
            System.out.println(board[i]);
        }
    }
}