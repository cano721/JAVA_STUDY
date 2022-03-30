class Solution {
    int[] dy = new int[]{-1,1,0,0};
    int[] dx = new int[]{0,0,-1,1};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Game game = cnt(board,aloc[0],aloc[1],bloc[0],bloc[1]);
        return game.cnt;
    }

    public Game cnt(int[][] board, int r1, int c1, int r2, int c2){
        int rlen = board.length;
        int clen = board[0].length;

        int maxCnt = 0;
        int minCnt = 1000;

        board[r1][c1] = 0;
        boolean win = false;
        boolean finish = true;
        
        for(int i=0; i<4; i++){
            int posY = r1 + dy[i];
            int posX = c1 + dx[i];
            if(posY<0||posY>=rlen||posX<0||posX>=clen||board[posY][posX]==0) continue;
            finish = false;
            
            Game game = cnt(board,r2,c2,posY,posX);
            if(!game.win) {
                win = true;
                minCnt = Integer.min(minCnt,game.cnt);
            }else{
                maxCnt = Integer.max(maxCnt,game.cnt);
            }
            
        }
        board[r1][c1] = 1;

        if(finish) return new Game(false, 0);
        if(r1==r2 && c1 == c2) return new Game(true, 1);
        
        if(win) return new Game(true ,minCnt+1);
        else return new Game(false, maxCnt+1);
    }
}

class Game{
    boolean win;
    int cnt;

    public Game(boolean w, int c){
        win = w;
        cnt = c;
    }
}