/*

    dp[숫자인덱스][더하거나 뺀 합] = 갯수

    처음숫자 8은 초기값 넣어두기
    dp[0][8] = 1

    두번째숫자 3
    dp[0][0~20]까지 돌면서 0이 아니면 +3 또는 -3하기
    가능한 숫자면 +1증가
    dp[1][5] = 1
    dp[1][11] = 1

    세번째숫자 2
    dp[2][7] = 1
    dp[2][3] = 1
    dp[2][13] = 1
    dp[2][9] = 1

    네번째 숫자 4

    dp[3][11] = 1
    dp[3][3] = 1
    dp[3][7] = 1
    dp[3][-1] 불가
    dp[3][17] = 1
    dp[3][9] = 1
    dp[2][13] = 1
    dp[2][5] = 1

    점화식

    // 숫자인덱스 1~마지막전까지 돌기
    for(int i = 1; i <n-1; i++){
        // 합친합 돌기
        for(int j = 0; j <= 20; j++){
            // 기존에 합친합이 있을때
            if(dp[i-1][j] != 0){
                // 현재숫자를 더했을 때 음수가 아니면서 20이하일때
                if(j+arr[i] >= 0 && j+arr[i] <=20){
                    // 기존갯수 더하기
                    dp[i][j+arr[i]]+=dp[i-1][j];;
                }
                // 현재숫자를 뺏을 때 음수가 아니면서 20이하일때
                 if(j-arr[i] >= 0 && j-arr[i] <=20){
                    // 갯수 증가
                    dp[i][j-arr[i]]+=dp[i-1][j];;
                }
            }
        }
    }

*/

import java.util.*;
import java.io.*;

public class BJ5557_1학년 {

    public static int n;
    public static long[][] dp;
    public static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        // dp 생성
        dp = new long[n-1][21];
        
        // 숫자 배열 넣기
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기값 넣기 0번째숫자
        dp[0][arr[0]] = 1;
        bottomUp();
        bw.write(dp[n-2][arr[n-1]] +"\n");

        bw.flush();
        bw.close();
    }

    public static void bottomUp(){

        for(int i = 1; i <n-1; i++){
            // 합친합 돌기
            for(int j = 0; j <= 20; j++){
                // 기존에 합친합이 있을때
                if(dp[i-1][j] != 0){
                    // 현재숫자를 더했을 때 음수가 아니면서 20이하일때
                    if(j+arr[i] >= 0 && j+arr[i] <=20){
                        // 기존 갯수 더하기
                        dp[i][j+arr[i]]+=dp[i-1][j];
                    }
                    // 현재숫자를 뺏을 때 음수가 아니면서 20이하일때
                     if(j-arr[i] >= 0 && j-arr[i] <=20){
                        // 기존 갯수 더하기
                        dp[i][j-arr[i]]+=dp[i-1][j];
                    }
                }
            }
        }
    }
}
