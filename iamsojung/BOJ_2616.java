package week1;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2616 {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] train = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            train[i] = Integer.parseInt(st.nextToken());
        }

        int len = Integer.parseInt(br.readLine());
        int[] sum = new int[n+1]; //1~n까지의 누적합 저장
        sum[0] = 0;
        for(int i=0; i<n; i++) {
            sum[i+1] = sum[i] + train[i];
        }

        int[][] dp = new int[4][n+1]; //i = 소형기관차 수, j = 객차 칸 번호 -> 최댓값 갱신해서 저장
        int res = 0;
        for(int i=1; i<4; i++){
            for(int j=1; j<n+1; j++) {
                dp[i][j] = 0;
                if(j >= len) { //객차 칸의 수(j)가 len 보다 클 때 갱신
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-len] + (sum[j] - sum[j-len]));
                }
                res = Math.max(dp[i][j], res);
            }
        }
        System.out.println(res);
    }
}