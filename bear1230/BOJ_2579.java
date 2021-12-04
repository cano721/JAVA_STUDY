/*
첫번째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값 출력

dp -> 최대점수
score -> 계단 점수
dp[n-2] + score[n]
dp[n-3] + score[n-1] + score[n]

*/

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] dp = new int[n+1];
        int[] score = new int[n+1]; 
        

        for(int i=1; i<=n; i++){
            score[i] = Integer.parseInt(br.readLine());  
        } 
             
        
        dp[1] = score[1];
        
        if(n>=2) 
            dp[2] = dp[1] + score[2];

        for(int i=3; i<=n; i++) 
            dp[i] = Math.max(dp[i-2] + score[i], dp[i-3] + score[i-1] + score[i]); 
        
        System.out.print(dp[n]);

    }
    
}