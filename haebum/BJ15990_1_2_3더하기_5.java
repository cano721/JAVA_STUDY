/*
    dp[정수][마지막더한수] = 방법의수

    dp[1][1] = 1 (1)

    dp[2][1] = 0
    dp[2][2] = 1 (2)
    dp[2][3] = 0

    dp[3][1] = 1 (2+1)
    dp[3][3] = 1 (3)
    dp[3][2] = 1 (1+2)


    dp[3][1] 을 보면
    마지막에 1을 더해서 3으로 와야하므로
    dp[2]에서만 올 수 있다.
    하지만 같은 수를 두번 이상 연속해서 사용할 수 없으므로
    dp[2][2](=1) 또는 dp[2][3](=0)에서만 올 수 있다.
    고로 dp[3][1] = 1

    아래와 같은 점화식 가능
    dp[n][1] = dp[n-1][2] + dp[n-1][3]
    dp[n][2] = dp[n-2][1] + dp[n-2][3]
    dp[n][3] = dp[n-3][1] + dp[n-3][2]
*/

import java.util.*;
import java.io.*;

public class BJ15990_1_2_3더하기_5 {

    public static int n,div;
    public static long[][] dp = new long[100_001][4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        div = 1_000_000_009;
        bottomUp();

        for(int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());
            long answer = (dp[n][1]+dp[n][2]+dp[n][3])%div;
            bw.write(answer+"\n");
        }
        bw.flush();
        bw.close();
    }


    public static void bottomUp(){
        // 초기값 설정
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        // 10만까지 dp 다 구해두기
        for(int i = 4; i <= 100_000; i++){
            dp[i][1] = (dp[i-1][2] + dp[i-1][3])%div;
            dp[i][2] = (dp[i-2][1] + dp[i-2][3])%div;
            dp[i][3] = (dp[i-3][1] + dp[i-3][2])%div;
        }
    }
}
