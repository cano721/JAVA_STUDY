/*
    dp[현재잔] = 현재잔까지의 최대마신 포도주 양

    현재잔을 마신다와 안마신다
    마실경우는
    1. 2번째전 포도주는 안마시고 전에꺼랑 이번꺼 마시기
    2. 전에 포도주를 안마시고 현재 포도주 마시기

    dp[n] = dp[n-3] +arr[n-1] +arr[n];
    dp[n] = dp[n-2] +arr[n];

    안마실경우는
    전까지 최대값
    dp[n] = dp[n-1];

    이 3가지경우 중 최대값으로 저장

*/

import java.util.*;
import java.io.*;

public class BJ2156_포도주시식 {

    public static int n;
    public static Integer[] arr,dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp = new Integer[n+1];
        arr = new Integer[n+1];
        for(int i = 1; i <= n; i++){
            int wine = Integer.parseInt(br.readLine());
            arr[i] = wine;
        }
        topDown(n);
        //bottomUp();
        System.out.println(dp[n]);
    }

    public static int topDown(int idx){
        if(dp[idx] != null){
            return dp[idx];
        }
        if(idx == 0){
            dp[0] = 0;
        }else if(idx == 1){
            dp[1] = arr[1];
        }else if(idx == 2){
            dp[2] = arr[1] +arr[2];
        }else{
            // 이전꺼랑 지금꺼 마시기
            dp[idx] = topDown(idx-3)+arr[idx-1]+arr[idx];
            // 이전꺼 안마시고 현재꺼 마시기
            dp[idx] = Math.max(dp[idx],topDown(idx-2)+arr[idx]);
            // 현재꺼 안마시기
            dp[idx] = Math.max(dp[idx],topDown(idx-1));
        }
        return dp[idx];
    }

    public static void bottomUp(){
        for(int i = 0; i <= n; i++){
            if(i == 0) dp[0] = 0;
            else if(i == 1) dp[1] = arr[1];
            else if(i == 2) dp[2] = arr[1] +arr[2];
            else{
                // 이전꺼랑 현재꺼 마실경우
                dp[i] = dp[i-3]+arr[i-1]+arr[i];
                // 이전꺼 안마시고 현재꺼 마실경우
                dp[i] = Math.max(dp[i],dp[i-2]+arr[i]);
                // 현재꺼 안마실 경우
                dp[i] = Math.max(dp[i],dp[i-1]);
            }
        }
    }
}
