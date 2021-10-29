/*
    
    재귀로 풀 예정
    각 원소를 고른것과 안고른것으로
    다음 스테이지로 넘어가서
    최종 스테이지 도달 시(n-1) 합이 s면 갯수 추가



*/


import java.util.*;
import java.io.*;

public class BJ1182_부분수열의합 {

    public static int n,s,cnt;
    public static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        arr = new int[n];

        cnt = 0;

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st2.nextToken());
            arr[i] = num;
        }
        for(int i = 0; i <n; i++){
            choice(arr[i],i);
        }

        // 정답 출력
        System.out.println(cnt);
    }

    public static void choice(int sum, int stage){

        // 마지막에 도달했을때
        if(stage == n-1){
            // 지금까지의 합계가 s라면 cnt 추가
            if(sum == s) cnt++;
            return;
        }

        // 다음 원소 고르고 넘어가기
        choice(sum+arr[stage+1],stage+1);
        // 다음 원소 안고르고 넘어가기
        choice(sum,stage+1);
    }

    
}
