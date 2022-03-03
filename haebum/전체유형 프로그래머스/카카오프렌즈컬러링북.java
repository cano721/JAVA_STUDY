/**
    1. bfs로 영역의 개수와 가장 큰 영역의 칸 수 반환
**/

import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        
        answer = bfs(m,n,picture);
        return answer;
    }
    
    public int[] bfs(int m, int n, int[][] picture){
        int cnt = 0;
        int max = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        
        boolean[][] visited = new boolean[m][n]; 
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j] == false && picture[i][j] != 0){
                    cnt++;
                    int size = 1;
                    visited[i][j] = true;
                    q.offer(new int[] {i,j,1});
                    
                    while(!q.isEmpty()){
                        
                        int[] cur = q.poll();
                        
                        int x = cur[0];
                        int y = cur[1];
                        
                        for(int idx = 0; idx < 4; idx++){
                            int nx = x + dirX[idx];
                            int ny = y + dirY[idx];
                            
                            if(nx < 0 || nx >= m || ny <0 || ny >= n) continue;
                            if(visited[nx][ny] == true) continue;
                            if(picture[nx][ny] != picture[x][y]) continue;
                            visited[nx][ny] = true;
                            size++;
                            q.offer(new int[] {nx,ny});
                        }
                    }
                    max = Math.max(max,size);
                }
            }
        }
        return new int[]{cnt,max};
    }
}