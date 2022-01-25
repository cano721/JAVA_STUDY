import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
       
        int n = Integer.parseInt(st.nextToken());  
        int s = Integer.parseInt(st.nextToken());  
        int m = Integer.parseInt(st.nextToken()); 

        
        int[] map = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][m+1];
        dp[0][s] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[i-1][j] == 0) {
                    continue;
                }
                
                if (j + map[i-1] <= m) {
                    dp[i][j + map[i-1]] = 1;
                }
                if (j - map[i-1] >= 0) {
                    dp[i][j - map[i-1]] = 1;
                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[n][i] != 0) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}

