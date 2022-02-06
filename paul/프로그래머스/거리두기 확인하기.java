import java.util.*;
class Solution {
    
    static class Pair{
        int y,x,d;
        public Pair(int yy, int xx, int dd){
            y = yy;
            x = xx;
            d = dd;
        }
    }
    
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    
    static boolean bfs(int y, int x, boolean[][] vis, String[] s){
        Queue<Pair> q= new LinkedList<>();
        q.add(new Pair(y,x,0));
        while(!q.isEmpty()){
            Pair now = q.poll();
            if(now.d >= 2) return true;
            for(int i =0; i < 4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if(yy<0 || xx < 0 || yy >4 || xx>4 || vis[yy][xx]) continue;
                if(s[yy].charAt(xx) == 'X') continue;
                if(s[yy].charAt(xx) == 'P') return false;
                vis[yy][xx] = true;
                q.add(new Pair(yy,xx, now.d+1));
            }
        }
        return true;
    }
    
    static int getAns(String[] s){
        boolean[][] dist = new boolean[5][5];
        for(int i =0; i<5; i++){
            for(int j =0; j<5; j++){
                if(dist[i][j] || s[i].charAt(j) != 'P') continue;
                dist[i][j] = true;
                if(bfs(i,j, dist, s) == false) return 0; 
            }
        }
        return 1;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i =0; i<5; i++){
            answer[i] = getAns(places[i]);
        }
        return answer;
    }
}