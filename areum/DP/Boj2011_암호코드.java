package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 다시 풀어보기
public class Boj2011_암호코드 {
    public static int mod = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pw = br.readLine();
        int len = pw.length();
        int[] dp = new int[len+1];

        // 0 으로 시작하는 경우
        if (pw.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        // dp 초기화
        // ex) 25114 에서 처음 2 탐색시 나올 수 있는 경우의 수 2 → 1가지
        dp[0] = 1;

        for (int i = 1; i <= len; i++) {

            // pw[i]를 한 자리 수인 경우로 인식
            int num = pw.charAt(i - 1) - '0';
            if (1 <= num && num <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= mod;
            }

            // pw[i-1], pw[i] 두 자리 수 인 경우로 인식
            if (i == 1)
                continue;

            // 두 자리 중 앞자리가 0이면 두자리수가 아니므로 continue
            if ((pw.charAt(i - 2) - '0') == 0)
                continue;

            int n = (pw.charAt(i - 2) - '0') * 10 + pw.charAt(i - 1) - '0';
            if (n <= 26) { // 두 자리수가 26이내인 경우
                dp[i] += dp[i - 2];
                dp[i] %= mod;
            }
        }

        System.out.println(dp[len]);
    }
}
