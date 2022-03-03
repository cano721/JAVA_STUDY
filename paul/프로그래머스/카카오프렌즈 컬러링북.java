import java.util.*;
class Solution {
    
    static boolean[][] vis;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    public static int bfs(int color, int y, int x, int m, int n, int[][] picture){
        int cnt = 0;
        Queue<Integer> qy = new LinkedList<Integer>();
        Queue<Integer> qx = new LinkedList<Integer>();
        qy.add(y);
        qx.add(x);
        vis[y][x] = true;
        
        while(qy.isEmpty() != true){
            y = qy.poll(); x = qx.poll();
            cnt++;
            for(int i =0; i<4; i++){
                int cy = dy[i] + y;
                int cx = dx[i] + x;
                if( cy < 0 || cy>=m || cx<0 || cx>=n) continue;
                if(vis[cy][cx] == true || picture[cy][cx] != color) continue;
                vis[cy][cx] = true;
                qy.add(cy);
                qx.add(cx);
            }
        }
        
        return cnt;
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        vis = new boolean[m][n];
        for(int i =0; i<m; i++){
            for(int j =0; j<n; j++){
                if(vis[i][j] || picture[i][j] == 0) continue;
                numberOfArea++;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(picture[i][j], i, j, m, n, picture));
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}