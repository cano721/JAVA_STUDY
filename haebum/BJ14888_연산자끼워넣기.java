/*
    
    재귀로 풀 예정
    사용갯수에서 차감하면서 돌기
    최종 목표치에 도달했을때 최대값과 최소값 변경

*/


import java.util.*;
import java.io.*;

public class BJ14888_연산자끼워넣기 {

    public static int n,maxNum,minNum;
    public static int[] arr,operator;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 숫자 개수
        n = Integer.parseInt(br.readLine());
        
        //배열 만들기
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        arr = new int[n];
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        
        // 연산자 개수 배열
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
        operator = new int[4];
        for(int i = 0; i < 4; i++){
            int num = Integer.parseInt(st2.nextToken());
            operator[i] = num;
        }
        //초기값 설정
        maxNum = Integer.MIN_VALUE;
        minNum = Integer.MAX_VALUE;
        choice(0,arr[0]);

        // 정답 출력
        System.out.println(maxNum);
        System.out.println(minNum);
    }

    public static void choice(int stage,int sum){

        // 마지막에 도달했을때
        if(stage == n-1){
            // 최대값 최소값 변경
            maxNum = Math.max(maxNum,sum);
            minNum = Math.min(minNum,sum);
            return;
        }

        for(int i = 0; i < 4; i++){
            if(operator[i] != 0){
                // 사용 체크
                operator[i]--;
                // 연산자에 따른 계산
                switch(i){
                    case 0:
                        choice(stage+1,sum+ arr[stage+1]);
                        break;
                    case 1:
                        choice(stage+1,sum - arr[stage+1]);
                        break;
                    case 2:
                        choice(stage+1,sum * arr[stage+1]);
                        break;
                    case 3:
                        choice(stage+1, sum / arr[stage+1]);
                        break;
                }
                //초기화
                operator[i]++;
            }
        }
    }
}
