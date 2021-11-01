/*
    n범위 2~10

    백트래킹으로 도시를 들릴때마다 방문횟수를 증가시켜 모든 도시를 방문했을때 코스트 출력

*/


import java.util.*;
import java.io.*;

public class BJ10971_외판원순회2 {

    public static int n,minCost;
    public static int[][] arr;
    public static int[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n][n];
        visited = new int[n];
        
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < n; j++){
                int cost = Integer.parseInt(st.nextToken());
                arr[i][j] = cost;
            }
        }

        // 초기값 설정
        minCost = Integer.MAX_VALUE;

        for(int startCity = 0; startCity < n; startCity++){
            solve(startCity,startCity,0,0);
        }
        System.out.println(minCost);
    }

    // 백트래킹
    public static void solve(int startCity,int city,int cost,int visitedNum){
     

        // 다 방문했으면 코스트 체크
        if(startCity == city && visitedNum == n){
            minCost = Math.min(minCost, cost);
            return;
        }

        // 도시를 돌면서 방문하지않았고 본인 도시가 아니면
        for(int i =0; i < n; i++){
            if(visited[i] == 0 && arr[city][i] != 0){
                visited[i] = 1;
                // 코스트 추가 및 방문횟수 증가시켜서 돌기
                solve(startCity,i,cost+arr[city][i],visitedNum+1);
                visited[i] = 0;
            }
        }
    }
}
