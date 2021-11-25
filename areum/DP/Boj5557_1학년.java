package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다시 풀어보기
public class Boj5557_1학년 {
    static int N;
    static int[] num;
    static long[] dp = new long[21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            num[i] =Integer.parseInt(st.nextToken());
        }

        dp[num[0]] = 1;
        for(int i=0; i<N-2; i++) {
            dp = cal(i+1);
        }

        System.out.println(dp[num[N-1]]);
    }

    public static long[] cal(int idx) {
        long[] tmp = new long[21];

        for(int i=0; i<21; i++) {
            // 범위내에 있을 때 다음 수에서 할 수 있는 +,- 연산
            if(dp[i] != 0) {
                if(i-num[idx] >= 0) {
                    tmp[i-num[idx]] += dp[i];
                }
                if(i+num[idx] < 21) {
                    tmp[i+num[idx]] += dp[i];
                }
            }
        }

        return tmp;
    }
}
