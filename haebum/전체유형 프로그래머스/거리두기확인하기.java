/**
    1. bfs 풀이
    1-1) 시간복잡도 체크 대기실 수 5 * 대기실크기 25 * 4방향 4 * 최대확인거리 2
    
    2. 대기실내에서 사람이 나올때마다 bfs로 거리안에 사람이 또 있는지 체크
    
    3. 각 대기실마다 체크 및 배열로 반환
**/

import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        
        int[] answer = new int[places.length];
        int idx = 0;
        for(String[] place : places){
            
            answer[idx++] = bfs(place);
        }
        
        return answer;
    }
    
    public int bfs(String[] place){
        
        Queue<int[]> q = new LinkedList<>();
        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        
        for(int i = 0; i < place.length; i++){
            for(int j = 0; j < place[0].length(); j++){
                if(place[i].charAt(j) == 'P'){
                    
                    boolean[][] visited = new boolean[place.length][place[0].length()];
                    visited[i][j] = true;
                    q.offer(new int[] {i,j,0});
                    
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
                        
                        if(cur[2] > 1){
                            continue;
                        }
                        
                        for(int idx = 0; idx < 4; idx++){
                            int nx = x + dirX[idx];
                            int ny = y + dirY[idx];
                            
                            if(nx < 0 || nx >= place.length || ny < 0 || ny >= place[0].length()){
                                continue;
                            }
                            
                            if(visited[nx][ny] == true){
                                continue;
                            }
                            
                            if(place[nx].charAt(ny) == 'X'){
                                continue;
                            }
                            
                            if(place[nx].charAt(ny) == 'P'){
                                return 0;
                            }
                            visited[nx][ny] = true;
                            q.offer(new int[] {nx,ny,cur[2]+1});
                        }
                    }
                }
            }
        }
        
        return 1;
    }
}