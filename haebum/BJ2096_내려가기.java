/*
    dp[현재층][현재자리] = 맥스값 또는 최소값
    
    dp[n][0] = Math.max(dp[n-1][0],dp[n-1][1]) + arr[n][0];

*/

import java.util.*;
import java.io.*;

public class BJ2096_내려가기 {

    public static int[][] dp;
    public static int[][] arr;
    public static int n,s,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        
        arr = new int[n][3];

        for(int i = 0; i < n; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxBottomUp();
        int max = Math.max(Math.max(dp[n-1][0],dp[n-1][1]),dp[n-1][2]);
        minBottomUp();
        int min = Math.min(Math.min(dp[n-1][0],dp[n-1][1]),dp[n-1][2]);

        System.out.println(max + " " + min);

    }
    //바텀업방식
    public static void maxBottomUp(){
        dp = new int[n][3];
        for(int i = 0; i < n; i++){
            if(i == 0){
                dp[0][0] = arr[0][0];
                dp[0][1] = arr[0][1];
                dp[0][2] = arr[0][2];
            }else{
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]) + arr[i][0];
                dp[i][1] = Math.max(Math.max(dp[i-1][0],dp[i-1][1]),dp[i-1][2]) + arr[i][1];
                dp[i][2] = Math.max(dp[i-1][1],dp[i-1][2]) + arr[i][2];
            }
        }
    }
    public static void minBottomUp(){
        dp = new int[n][3];
        for(int i = 0; i < n; i++){
            if(i == 0){
                dp[0][0] = arr[0][0];
                dp[0][1] = arr[0][1];
                dp[0][2] = arr[0][2];
            }else{
                dp[i][0] = Math.min(dp[i-1][0],dp[i-1][1]) + arr[i][0];
                dp[i][1] = Math.min(Math.min(dp[i-1][0],dp[i-1][1]),dp[i-1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i-1][1],dp[i-1][2]) + arr[i][2];
            }
        }
    }
}