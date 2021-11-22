/*
    n이 40이므로 피보나치 40까지 구한 것들을
    배열에 저장해두고 불렀을때 해당한것을 이미 구해놨다면
    그냥 가져오기

    탑다운 방식과 바텀업방식 둘다 풀것
*/

import java.util.*;
import java.io.*;

public class BJ1003_피보나치함수 {

    public static int[][] dp = new int[41][2];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < 41; i++){
            Arrays.fill(dp[i],-1);
        }

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;
        // // 바텀업 방식
        // for(int i = 2; i < 41; i++){
        //     dp[i][0] = dp[i-1][0] + dp[i-2][0];
        //     dp[i][1] = dp[i-1][1] + dp[i-2][1];
        // }

        
        // 탑다운
        fibo(40);

        for (int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n][0] + " " + dp[n][1] + "\n");
        }
        bw.flush();
        bw.close();
    }

    //탑다운방식
    public static int[] fibo(int n){

        if(dp[n][0] == -1 || dp[n][1] == -1){
            dp[n][0] = fibo(n-1)[0] + fibo(n-2)[0];
            dp[n][1] = fibo(n-1)[1] + fibo(n-2)[1];
        }
        return dp[n];
    }
}
