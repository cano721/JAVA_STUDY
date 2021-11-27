package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// ** 배낭 문제
public class Boj1535_안녕 {
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] energy = new int[N+1];
        int[] happy = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            energy[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][101];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=100; j++) {
                if(j-energy[i]>0) { // 체력이 남아있을 경우
                    dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-energy[i]] + happy[i]);
                }
                else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][100]);
    }
}
