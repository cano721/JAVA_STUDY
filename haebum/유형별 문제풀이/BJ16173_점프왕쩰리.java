/*
    맵의 범위 3*3

    dfs와 bfs를 통해 최종 목적지에 도달했는지 확인
*/


import java.util.*;
import java.io.*;

public class BJ16173_점프왕쩰리 {

    public static int n;
    public static Boolean check;
    public static int[][] arr;
    public static Boolean[][] visited;
    public static int[] dirX = {0,1};
    public static int[] dirY = {1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n][n];
        visited = new Boolean[n][n];
        
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
            }
        }


        // 초기값 설정
        check = false;
        // Arrays.fill(visited,false);
        for(int i = 0; i < n; i++){
            Arrays.fill(visited[i],false);
        }

        //dfs(0,0);
        bfs();

        if(check) System.out.println("HaruHaru");
        else System.out.println("Hing");
        
    }

    // dfs
    public static void dfs(int x,int y){
        // 끝지점 방문 시 도달 가능한걸로 표기
        visited[x][y] = true;
        if(arr[x][y] == -1){
            check = true;
            return;
        }

        int num = arr[x][y];
        // 오른쪽 가기(가야할 곳이 방문하지 않았을때)
        if(y+num < n && !visited[x][y+num]) dfs(x,y+num);
        // 아래로 가기
        if(x+num < n  && !visited[x+num][y]) dfs(x+num,y);
    }

    // bfs
    public static void bfs(){
        // 큐생성 및 시작 좌표 담기
        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new int[] {0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int num = arr[x][y];
            // 끝 도달 시 종료
            if(num == -1){
                check = true;
                return;
            }

            // 아래,오른쪽 방향 돌기
            for(int idx = 0; idx < 2; idx++){
                int newX = x+dirX[idx]*num;
                int newY = y+dirY[idx]*num;
                // 맵 안에 있으면서 방문하지 않았던 곳이면 방문
                if(0 <= newX && newX < n && 0 <= newY && newY < n && !visited[newX][newY]){
                    visited[newX][newY] = true;
                    // 큐에 추가
                    q.offer(new int[] {newX,newY});
                }

            }

        }
    }
}
