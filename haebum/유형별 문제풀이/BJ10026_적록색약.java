/*
   bfs를 통해 구역을 나누면서 횟수를 셀 예정

   하지만 적록색약인 사람과 아닌 사람이 있으므로

   적록색약인 버전으로 맵을 하나 더 만들 예정!

   두개의 맵을 bfs 돌기!
*/


import java.util.*;
import java.io.*;

public class BJ10026_적록색약 {

    public static int n;
    public static int[][] arr,arr2,visited;
    public static int[] dirX = {0,0,1,-1};
    public static int[] dirY = {1,-1,0,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 사람수
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n][n]; // 정상인 맵
        arr2 = new int[n][n]; // 적록색약 맵
        visited = new int[n][n];
        // 맵 생성
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < str.length(); j++){
                // 정상인 맵 생성
                arr[i][j] = str.charAt(j);

                // 적록색약의 경우 빨간색 초록색은 빨간색으로 통일해서 맵 생성
                if(str.charAt(j) != 'B'){
                    arr2[i][j] = 'R';
                }else{
                    arr2[i][j] = 'B';
                }
            }
        }
        int answer1 = bfs(arr);
        // visited 초기화
        for(int i = 0; i < n; i++){
            Arrays.fill(visited[i],0);
        }
        int answer2 = bfs(arr2);
        System.out.println(answer1 + " " + answer2);
    }

    // bfs
    public static int bfs(int[][] maps){
        // 큐생성 및 시작 좌표 담기
        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // 영역 체크
                if(visited[i][j] == 0){
                    cnt++;
                    visited[i][j] = 1;
                    q.offer(new int[] {i,j});

                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
                        int color = maps[x][y];

                        // 4방향 이동
                        for(int idx = 0; idx <4; idx++){
                            int newX = x + dirX[idx];
                            int newY = y + dirY[idx];
                            // 맵안에 있을때
                            if(0 <= newX && newX < n && 0 <= newY && newY < n){
                                // 방문하지 않았고 같은 컬러일때
                                if(visited[newX][newY] == 0 && maps[newX][newY] == color){
                                    visited[newX][newY] = 1;
                                    q.offer(new int[] {newX,newY});
                                }
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
