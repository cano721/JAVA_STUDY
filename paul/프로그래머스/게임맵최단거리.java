import java.util.*;

//게임맵최단거리
class Solution {
    
    static class Pair{
        int y,x, d;
        public Pair(int yy, int xx, int dd){
            y =yy;
            x = xx;
            d = dd;
        }
    }
    
    public int solution(int[][] maps) {
        int answer = -1;
        int n = maps.length;
        int m = maps[0].length;
        int dy[] = {0,0,1,-1};
        int dx[] = {1,-1,0,0};
        boolean[][] dist = new boolean[n][m];
        
        Queue<Pair> q = new LinkedList<>();
        dist[0][0] = true;
        q.add(new Pair(0,0,1));
        
        
        while(!q.isEmpty()){
            Pair now = q.poll();
            if(now.y == n-1 && now.x == m-1){
                answer = now.d;
                break;
            }
            for(int i =0; i<4;i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if( yy < 0 || xx< 0 || yy>=n || xx>=m || dist[yy][xx]) continue;
                if(maps[yy][xx] == 0) continue;
                dist[yy][xx] =true;
                q.add(new Pair(yy,xx, now.d + 1));
            }
        }
        
                
        return answer;
    }
}