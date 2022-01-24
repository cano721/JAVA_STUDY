/*
    맵의 범위 100*100

    dfs와 bfs를 통해 공기면 이동(맨 가장자리는 치즈가 놓이지 않는다고 함 0,0 시작)
    치즈면 visited를 증가.

    visited체크 함수를 둬서 2이상인 것은 치즈 녹이기

    다시 반복해서 전체 녹으면 시간 출력

    1. 공기 이동하면서 치즈일 경우 visited 증가 함수

    2. visited 체크 함수 (2일 경우 녹이기 치즈가 없을경우 시간 출력)

    3. while문으로 반복하면서 시간 증가
    
*/


import java.util.*;
import java.io.*;

public class BJ2638_치즈 {

    public static int n,m,answer;
    public static int[][] arr,visited;
    public static int[] dirX = {0,1,0,-1};
    public static int[] dirY = {1,0,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 배열크기 생성
        arr = new int[n][m];
        visited = new int[n][m];
        
        for(int i = 0; i < n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        while(true){
            bfs();
            // 치즈 녹인 개수 반환
            int num = melt();
            // 비지티드 초기화
            for(int i = 0; i < n; i++){
                Arrays.fill(visited[i],0);
            }
            // 치즈 녹인 개수가 0개이면 와일문 종료
            if(num == 0) break;
            // 시간 증가
            answer++;
        }
        System.out.println(answer);
        
    }
    // 공기이동 함수
    public static void bfs(){
        // 큐생성 및 시작 좌표 담기
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = 1;
        q.offer(new int[] {0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            // 방향 돌기
            for(int idx = 0; idx < 4; idx++){
                int newX = x+dirX[idx];
                int newY = y+dirY[idx];
                // 맵 안에 있으면서
                if(0 <= newX && newX < n && 0 <= newY && newY < m){
                    // 공기일때
                    if(arr[newX][newY] == 0 && visited[newX][newY] == 0){
                        visited[newX][newY] = 1;
                        q.offer(new int[] {newX,newY});
                    // 치즈일땐 visited 숫자 증가
                    }else if(arr[newX][newY] == 1){
                        visited[newX][newY]++;
                    }
                }
            }

        }
    }

    public static int melt(){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] >= 2){
                    arr[i][j] = 0;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
