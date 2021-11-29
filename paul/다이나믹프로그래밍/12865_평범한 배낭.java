import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] weight = new int[n+1];
        int[] price = new int[n+1];
        
        for(int i =1; i<=n; i++){
            st= new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[n+1][k+1];
        int ans = 0;
        for(int i=1; i<=n; i++){
            for(int w =1; w<=k; w++){
                if(w < weight[i]) dp[i][w] = dp[i-1][w];
                else dp[i][w] = Math.max(dp[i-1][w-weight[i]] + price[i], dp[i-1][w]); 
                if(i == n) ans = Math.max(dp[i][w], ans);
            }
        }

        
        System.out.println(ans);

    }
}

