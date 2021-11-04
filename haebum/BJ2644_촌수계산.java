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

public class BJ2644_촌수계산 {

    public static int n,cnum1,cnum2,chon1,chon2;
    public static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 사람수
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n+1][n+1];
        
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
        }



        // 초기값 설정

        int answer = Integer.MAX_VALUE;

        // 하나씩 넣어보면서 dfs 돌아보기
        for(int i =1; i <= n; i++){
            // 초기값 설정
            chon1 = -1;
            chon2 = -1;
            dfs1(i,0); // 1번사람 구하기
            dfs2(i,0); // 2번사람 구하기
            if(chon1 != -1 && chon2 != -1){
                answer = Math.min(answer,chon1+chon2);
            }
        }
        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }

    // dfs
    public static void dfs1(int x,int cnt){
        // 구하고자 하는 촌수사람 나왔을시 촌수체크
        if(x == cnum1){
            chon1 = cnt;
            return;
        }
        for(int i = 1; i <= n; i++){
            if(arr[x][i] == 1){
                dfs1(i,cnt+1);
            }
        }
    }

    public static void dfs2(int x,int cnt){
        // 구하고자 하는 촌수사람 나왔을시 촌수체크
        if(x == cnum2){
            chon2 = cnt;
            return;
        }
        for(int i = 1; i <= n; i++){
            if(arr[x][i] == 1){
                dfs2(i,cnt+1);
            }
        }
    }
}
