import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        

        long[][] dp = new long[n+1][21];

        dp[0][arr[0]] = 1;

        for(int i = 1; i<= n-2; i++){
            int k = arr[i];
            for(int j= 0; j<21; j++){
                if(dp[i-1][j] > 0){
                    if( j - k >=  0) dp[i][j - k] += dp[i-1][j];
                    if( j + k <= 20) dp[i][j+k] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[n-2][arr[n-1]]);
    }
   
}