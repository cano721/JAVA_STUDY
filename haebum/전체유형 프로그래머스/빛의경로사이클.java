/**
    
    3차원 풀이 또는 2차원 + 비트마스크
    
    1. 최대 500 * 500 배열 존재
    
    2. 칸마다의 조건에 따라 4방향 돌면서 이동
    
    3. 들린 칸은 체크할건데, 어떤 방향에서 들렸는지를 체크(bit mask 사용할 예정)
    
    4. 다음 갈곳은 %연산으로 처리
    
    5. 같은 방향에서 다시 들리면 해당 사이클을 순환사이클로 몇개 들려서 왔는지 arrayList에 담기
    
    6. 다음 방향 체크(이미 쓴 경로는 못 씀)
    
    7. 최종 오름차순 경로 사이클 배열 반환
    
**/

import java.util.*;

class Solution {
    
    public int[][] visited;
    
    public ArrayList<Integer> ans = new ArrayList<>();
    
    public int w,h;
    
    
    
    public int[] solution(String[] grid) {
        
        visited = new int[grid.length][grid[0].length()];
        
        // 열 최대 길이
        h = grid.length;
        // 행 최대 길이
        w = grid[0].length();
        
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                for(int d = 0; d < 4; d++){
                    // 해당 위치에 해당방향에서 온적 있는지 체크
                    if((visited[i][j] & (1<<d)) == 1<<d){
                        continue;
                    }
                    int cnt = bfs(i,j,d,grid);
                    ans.add(cnt);
                }
            }
        }
        
        int[] answer = new int[ans.size()];
        
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    public int bfs(int x, int y, int d, String[] grid){
        // 오른쪽으로 도는 구조(동,북,서,남)
        int[] dirX = {0,1,0,-1};
        int[] dirY = {1,0,-1,0};
        
        int cnt = 0;
            
        while(true){
            // 왔던 곳에 같은 방향으로 오면 사이클
            if((visited[x][y] & (1<<d)) == 1<<d){
                break;
            }
            //거리 증가 및 방문처리
            cnt++;
            visited[x][y] |= (1<<d);
            
            // 방향 회전
            if(grid[x].charAt(y) == 'L'){
                d = d == 0 ? 3 : d-1;
            }else if(grid[x].charAt(y) == 'R'){
                d = d == 3 ? 0 : d+1;
            }
            
            x = (x + dirX[d] + h) % h;
            y = (y + dirY[d] + w) % w;
        }
        
        return cnt;
    }
}