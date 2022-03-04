class Solution {
    static boolean[][] vis= new boolean[121][121];
    // up, donw, right, left
    static int[] dy = {-1, 1, 0, 0}; 
    static int[] dx = {0, 0, 1, -1};
    
    public int solution(String dirs) {
        int answer = 0;
        //시작 지점.
        int ny =5, nx = 5;
        for(int i =0; i< dirs.length(); i++){
            char k = dirs.charAt(i);
            int dir = getDir(k);
            int yy = ny+dy[dir], xx = nx+dx[dir];
            if(yy <0 || xx < 0 || yy> 10 || xx > 10) continue;
            int from = ny*11 + nx;
            int to = yy*11 + xx;
            if(!vis[from][to]) {
                answer++;
                vis[from][to] = vis[to][from] = true;
            }
            ny = yy;
            nx = xx;
        }
        
        
        return answer;
    }
    
    static int getDir(char k){
        switch(k){
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'R':
                return 2;
            case 'L':
                return 3;
        }
        return 4;
    }
}