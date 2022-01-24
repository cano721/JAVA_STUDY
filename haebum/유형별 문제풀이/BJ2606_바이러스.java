/*
    맵의 범위 100*100

    dfs와 bfs를 통해 최종 바이러스 숫자 확인
*/


import java.util.*;
import java.io.*;

public class BJ2606_바이러스 {

    public static int n,answer;
    public static int[][] arr;
    public static Boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n+1][n+1];
        visited = new Boolean[n+1];

        int k = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x][y] = 1;
            arr[y][x] = 1;
        }


        // 초기값 설정
        Arrays.fill(visited,false);


        //dfs(1);
        bfs();

        System.out.println(answer);
        
    }

    // dfs
    public static void dfs(int x){
        visited[x] = true;

        // 방문하지않은 컴퓨터고 갈 수 있으면 바이러스 전파
        for(int idx = 1; idx <= n; idx++){
            if(visited[idx] == false && arr[x][idx] == 1){
                answer++;
                dfs(idx);
            }
        }
    }

    // bfs
    public static void bfs(){
        // 큐생성 및 시작 컴퓨터 담기
        Queue<Integer> q = new LinkedList<>();
        visited[1] = true;
        q.offer(1);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int idx = 1; idx <= n; idx++){
                if(visited[idx] == false && arr[cur][idx] == 1){
                    visited[idx] = true;
                    answer++;
                    q.offer(idx);
                }
            }
        }
    }
}