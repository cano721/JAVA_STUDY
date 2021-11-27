import java.util.*;
import java.io.*;
/**
 *  dp[n] = k, n의 피로도가 남았을 때 K의 행복감을 느낀다.
 *  점화식 : dp[j] = max(dp[j-L[i]]+J[i] , dp[j])
 *          j의 피로도는 j-L[i]의 피로도에서 J[i]의 행복을 느낀것의 최대값.
 */
public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        int[] L = new int[n];
        int[] J = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i< n; i++){
            L[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            J[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[101];
        
        for(int i = 0; i<n; i++){
            for(int j = 100; j >= L[i]; j--){
                dp[j] = Math.max(dp[j-L[i]] + J[i], dp[j]);
            }
        }

        int ans = 0;
        for(int i =1; i<100; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

}