/*
   2*n 크기의 직사각형

   n = 1  1개 2_1
   n = 2  2개 2_1*2, 1_2*2
   n = 3  3개 (2_1) + 1_2*2, (2_1) + 2_1*2, [동일(2_1*2) +2_1], (1_2*2)+2_1
   n = 4  5개 3에서 2_1 더한것과 2에서 1_2*2한거 더하면 답(2에서 2_1*2 더한건 3이랑 중복됨)

   점화식 dp[n] = dp[n-1] + dp[n-2];
   */

import java.util.*;
import java.io.*;

public class BJ11726_2xN타일링 {

    public static long[] dp;
    public static int n,div;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
        n = Integer.parseInt(br.readLine());
        dp = new long[n+1];
        div = 10007;

        bottomUp();
        bw.write(dp[n] + "\n");
        bw.flush();
        bw.close();
    }
    
    //바텀업방식
    public static void bottomUp(){
        for(int i = 1; i <=n; i++){
            if(i == 1) dp[1] = 1;
            else if(i == 2) dp[2] = 2;
            else{
                dp[i] = (dp[i-1] +dp[i-2])%div;
            }
        }
    }
}
