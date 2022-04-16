import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] T = new int[n+1];
        int[] P = new int[n+1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];  
        dp[0] = 0;
        int ans = dp[0];
        for(int i = 1; i <= n; i++) {
            if(i + T[i] <= n+1) {  
                for(int j = 0; j < i; j++) {
                    if(j + T[j] <= i) { 
                        dp[i] = Math.max(dp[i], dp[j] + P[i]);
                    }
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        System.out.println(ans);
    }
}