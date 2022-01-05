package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10211 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            int answer = 0;
            int N = Integer.parseInt(br.readLine());
            int[] num = new int[N];
            int[] dp = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            int max = num[0];
            dp[0]=num[0];
            for (int i = 1; i < num.length; i++) {
                if(dp[i-1]<0){
                    dp[i-1]=0;
                }
                dp[i]=dp[i-1]+num[i];
                max = Math.max(max,dp[i]);
            }
            System.out.println(max);

        }


    }
}
