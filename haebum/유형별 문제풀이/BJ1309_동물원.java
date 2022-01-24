/*
   우리의 크기 n

   dp[n][가로칸] = 경우의수

   dp[1][0] = 1 사자가 없는경우
   dp[1][1] = 1 사자가 왼쪽에 있는경우
   dp[1][2] = 1 사자가 오른쪽에 있는경우

   // 어디서든 가능
   dp[2][0] = dp[1][0] + dp[1][1] + dp[1][2]

   // 이전에 오른쪽에만 사자가 있을때나 아예 없었을때 가능
   dp[2][1] = dp[1][0] + dp[1][2]

   // 이전에 왼쪽에만 사자가 있을때나 아예 없었을때 가능
   dp[2][2] = dp[1][0] + dp[1][1]

   점화식 생성

    3*n;
*/

import java.util.*;
import java.io.*;

public class BJ1309_동물원 {

    public static int[][] dp;
    public static int n,div;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
        // 우리크기
        n = Integer.parseInt(br.readLine());
        
        dp = new int[n][3];
        //초기값
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;
        div = 9901;

        bottomUp();
        // 마지막 값 중 최대값 출력
        int answer = dp[n-1][0] + dp[n-1][1] + dp[n-1][2];
        bw.write(answer%div + "\n");
        bw.flush();
        bw.close();
    }
    //바텀업방식
    public static void bottomUp(){
        for(int i = 1; i <n; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%div;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2])%div;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1])%div;
        }
    }
}
