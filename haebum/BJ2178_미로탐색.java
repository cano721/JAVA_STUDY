/*
    맵의 범위 100*100

    dfs와 bfs를 통해 최종 목적지에 도달했을때 이동칸수 확인

    dfs는 시간초과뜸...아마 4가지 경로로 계속반복되면서 10억 넘는듯.. 4*4*4*4*4*4*4....
    bfs만 가능
    
*/


import java.util.*;
import java.io.*;

public class BJ2178_미로탐색 {

    public static int n,m,answer;
    public static int[][] arr;
    public static Boolean[][] visited;
    public static int[] dirX = {0,1,0,-1};
    public static int[] dirY = {1,0,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        // 배열크기 생성
        arr = new int[n][m];
        visited = new Boolean[n][m];
        
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                arr[i][j] = str.charAt(j)-'0';
            }
        }

        // 초기값 설정
        for(int i = 0; i < n; i++){
            Arrays.fill(visited[i],false);
        }
        answer = Integer.MAX_VALUE;
        //dfs(0,0,1);
        bfs();
        System.out.println(answer);
        
    }

    // dfs 시간초과
    // public static void dfs(int x,int y,int cnt){
    //     // 끝지점 방문 시 도달 가능한걸로 표기
    //     if(x == n-1 && y == m-1){
    //         answer = Math.min(answer,cnt);
    //         return;
    //     }

    //     for(int idx = 0; idx < 4; idx++){
    //         int newX = x + dirX[idx];
    //         int newY = y + dirY[idx];
    //         // 맵안에 있을때
    //         if(0 <= newX && newX < n && 0 <= newY && newY < m){
    //            // 방문하지않았고 갈 수 있는 곳일때
    //            if(arr[newX][newY] == 1 && visited[newX][newY] == false){
    //                visited[x][y] = true;
    //                dfs(newX,newY,cnt+1);
    //                visited[x][y] = false;
    //            }
    //         }
    //     }
    // }

    // bfs
    public static void bfs(){
        // 큐생성 및 시작 좌표 담기
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new int[] {0,0,1});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];
            // 끝 도달 시 종료
            if(x == n-1 && y == m-1){
                answer = Math.min(answer,cnt);
                return;
            }

            // 방향 돌기
            for(int idx = 0; idx < 4; idx++){
                int newX = x+dirX[idx];
                int newY = y+dirY[idx];
                // 맵 안에 있으면서
                if(0 <= newX && newX < n && 0 <= newY && newY < m){
                    // 방문하지않았고 갈 수 있는곳일때
                    if(arr[newX][newY] == 1 && visited[newX][newY] == false){
                        visited[newX][newY] = true;
                        // 큐에 추가
                        q.offer(new int[] {newX,newY,cnt+1});
                    }
                }

            }

        }
    }
}
