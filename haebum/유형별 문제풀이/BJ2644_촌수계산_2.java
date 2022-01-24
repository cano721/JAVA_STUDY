/*
    범위 N
    dfs와 bfs를 통해 돌면서 촌수 증가()
    n이 100까지밖에 안되므로
    1~100까지 넣어가면서 체크(구하고자하는 촌수 둘다 나와야 정답)
    그때 둘의 촌수를 더하면 됨.
    dfs와 bfs를 통해 최종 목적지에 도달했는지 확인


    1. 단방향으로 설정 후 visited를 안쓰고 각 사람별로 촌수를 구해서 함(사람별 dfs 만들기!)

    2. 양방향으로 설정 후 visited를 써서 사람에서 사람으로 가는데 촌수 구하기(bfs로 풀 예정)
*/


import java.util.*;
import java.io.*;

public class BJ2644_촌수계산_2 {

    public static int n,cnum1,cnum2,answer;
    public static int[][] arr;
    public static int[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 사람수
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n+1][n+1];
        visited = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        
        // 촌수 계산할 2명
        cnum1 = Integer.parseInt(st.nextToken());
        cnum2 = Integer.parseInt(st.nextToken());

        // 관계정보 설정
        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;
        }
        // 초기값 설정
        answer = -1;

        bfs();
        System.out.println(answer);
    }

    // bfs
    public static void bfs(){
        // 큐생성 및 시작 좌표 담기
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {cnum1,0});
        visited[cnum1] = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int people = cur[0];
            int cnt = cur[1];
            // 끝 도달 시 종료
            if(people == cnum2){
                answer = cnt;
                return;
            }
            
            for(int i = 1; i <= n; i++){
                if(visited[i] == 0 && arr[people][i] == 1){
                    visited[i] = 1;
                    q.offer(new int[] {i,cnt+1});
                }
            }
        }
    }
}
