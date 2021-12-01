package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1309_동물원 {
    static final int DIV = 9901;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 우리의 크리
        dp = new int[N+1][3];

        // 사자가 첫줄에 있는 경우
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;

        for(int i=2; i<=N; i++) {
            // 사자가 없어도 되는 경우
            // i번째에는 넣지 않을 것이기 때문에 전에 사자가 어디있는지 상관없음
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % DIV;

            // 1번째 칸에 사자가 있는 경우
            // i번째에 1번방에 넣을 것이므로 이전에 1번 방에 있으면 안됨
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % DIV;

            // 2번째 칸에 사자가 있는 경우
            // i번째에 2번방에 넣을 것이므로 이전에 2번 방에 있으면 안됨
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % DIV;
        }

        System.out.println( (dp[N][0]+dp[N][1]+dp[N][2]) % DIV);
    }
}
