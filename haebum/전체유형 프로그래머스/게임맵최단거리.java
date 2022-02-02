/**
    1. bfs 풀이
**/

import java.util.*;

class Solution {
    
    public int[] dirX = {0,0,1,-1};
    public int[] dirY = {1,-1,0,0};
    public int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maps) {
        bfs(maps);
        if(answer == Integer.MAX_VALUE){
            return -1;
        }
        return answer;
    }
    
    public void bfs(int[][] maps){
        
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[maps.length][maps[0].length];
        visited[0][0] = 1;
        q.offer(new int[] {0,0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int x = cur[0];
            int y = cur[1];
            
            if(x == maps.length-1 && y == maps[0].length-1){
                answer = visited[x][y];
                return;
            }
            
            for(int i = 0; i < 4; i ++){
                int nx = dirX[i] + x;
                int ny = dirY[i] + y;
                
                if(nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length){
                    continue;
                }
                
                if(visited[nx][ny] != 0){
                    continue;
                }
                
                if(maps[nx][ny] == 0){
                    continue;
                }
                
                visited[nx][ny] = visited[x][y] +1;
                
                q.offer(new int[] {nx,ny});
            }
        }
    }
}