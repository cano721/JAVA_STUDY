import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException {
        int N, M, ans = Integer.MAX_VALUE;
        int[] m, c;
        int[][] dp;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        m = new int[N+1];
        c = new int[N+1];
        dp = new int[N+1][10001];
        st = new StringTokenizer(br.readLine());
        for(int i =1; i<= N; i++){
            m[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i =1; i<= N; i++){
            c[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=N; i++){
            for(int j=0; j <= 10000; j++){
                if(c[i] <= j) dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-c[i]] + m[i]);
                else dp[i][j] = dp[i-1][j];

                if(dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);

    }
}