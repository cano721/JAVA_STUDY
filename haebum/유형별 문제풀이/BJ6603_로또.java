/*
    임의의 숫자 리스트중에서 k 개 뽑기
    n과 m 이용

    중복 케이스 제거 및 오름차순 출력(주어진 집합이 오름차순 정렬되어있음)

*/


import java.util.*;
import java.io.*;

public class BJ6603_로또 {

    //인원수
    public static int n;

    // 숫자 배열
    public static int[] num;
    
    // 방문여부 체크 배열
    public static int[] visited;

    // 초이스 배열
    public static int[] choice = new int[6];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            // s의 원소 개수
            n = Integer.parseInt(st.nextToken());
            // 0이면 테스트 종료
            if(n == 0) break;

            // 원소의 개수만큼 배열크기 지정
            num = new int[n];
            visited = new int[n];
            // 원소 배열에 담기
            for(int i = 0; i < n; i++){
                num[i] = Integer.parseInt(st.nextToken());
            }
            solve(0,0);
            System.out.println();
        }
        

    }
    // pnum으로 인해 기존에 골랐던 번호부터 시작
    // 기존에 골랐던건 사용한것으로 처리되어있으므로
    // 사실상 기존에 고른거+1부터 시작
    public static int solve(int stage,int pnum){

        // 다 골랐을때
        if(stage == 6){
            // 선택배열 돌면서 출력
            for(int i = 0; i < choice.length; i++){
                System.out.print(choice[i] + " ");
            }
            System.out.println();
            return 0;
        }
        
        // 숫자를 돌면서 고르기
        for(int i = pnum; i < n; i++){
            // 고르지 않은 숫자일때
            if(visited[i] == 0){
                // 사용한것으로 체크
                visited[i] = 1;
                // 선택 숫자 담기
                choice[stage] = num[i];
                // 다음 숫자 고르러 가기
                solve(stage+1,i);
                // 초기화
                visited[i] = 0;
            }
        }
        return 0;
    }
}