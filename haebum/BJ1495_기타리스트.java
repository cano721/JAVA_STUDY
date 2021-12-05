/*
    바텀업으로 노래 리스트 돌리기

    dp[현재노래][볼륨크기] = 가능여부

    실패 다시 풀기
    

*/

import java.util.*;
import java.io.*;

public class BJ1495_기타리스트 {

    public static long[][] dp;
    public static long[] arr;
    public static int n,s,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dp = new long[n+1][2];


        arr = new long[n+1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        bottomUp();
        if(dp[n][0] == -1 && dp[n][1] == -1){
            bw.write(-1 + "\n");
        }else{
            bw.write(Math.max(dp[n][0],dp[n][1]) + "\n");
        }
        bw.flush();
        bw.close();
    }
    //바텀업방식
    public static void bottomUp(){
        for(int i = 0; i < n+1; i++){
            
        }
    }
}