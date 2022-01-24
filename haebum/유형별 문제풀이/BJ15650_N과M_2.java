/*
   1~N중에서 M개를 고른 수열
   오름차순 정렬이고 중복 없으므로
   visited 개념으로 리스트를 만들어서 사용했으면 넘어갈것
   그리고 다음 고른 숫자가 기존에 고른것보다 작으면 넘어갈것

*/


import java.util.*;
import java.io.*;

public class BJ15650_N과M_2 {

    public static int n;
    public static int m;
    
    // 숫자 배열
    public static int[] arr;

    // 선택숫자 배열
    public static int[] choice;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        //숫자 배열 길이 생성
        arr = new int[n+1];

        // 선택담을 배열 생성
        choice = new int[m];

        solve(0);
    }

    public static int solve(int stage){

        // 다 골랐을때
        if(stage == m){
            //선택 배열 돌면서 출력
            for(int idx = 0; idx < m; idx++){
                System.out.print(choice[idx] + " ");
                }
            System.out.println();
            return 0;
        }
        
        // 숫자를 돌면서 고르기
        for(int i =1; i <= n; i++){
            // 고르지 않은 숫자일때
            if(arr[i] == 0){
                // 한개이상의 숫자를 골랐다면 현재 고른 숫자가
                // 기존의 고른 숫자보다 커야함 크지않으면 다음숫자확인
                if(stage > 0 && i < choice[stage-1]) continue;

                // 고른숫자 담기
                choice[stage] = i;
                // 숫자 사용한것으로 표기
                arr[i] = 1;
                // 다음숫자 고르러 가기
                solve(stage+1);
                // 초기화
                arr[i] = 0;
            }
        }
        return 0;
    }
}
