package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 다시 풀어볼 것
public class Boj15990_123더하기5 {
    static long div = 1000000009;
    // 같은 수가 연속해서 나오면 X
    // → 연산의 끝이 무엇인지로 구분하기 위해
    // → dp[n][1], dp[n][2], dp[n][3] 구분
    static long[][] dp = new long[100001][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= 100000; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % div;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % div;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % div;
        }

        for(int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println((dp[N][1] + dp[N][2] + dp[N][3]) % div);
        }
    }
}
