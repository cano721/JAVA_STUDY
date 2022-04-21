import java.util.*;
import java.io.*;

/**
 *  블로그를 참고해서 품.
 *  테이블 정의 : dp[n] = k, n자리 수를 봤을 때 해석될 수 있는 암호의 개수. 
 *  점화식 : dp[n] = dp[n] + dp[n-1] // 1자릿수 일때 (0 이 아닐 때)
 *         dp[n] = dp[n] + dp[n-2] // 2자릿수로 해석 [10, 26]
 */

public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        int[] dp = new int[n+1];
        int[] arr = new int[n+1];
        final int MOD = (int)1e6;
        for(int i =1; i<=n; i++){
            arr[i] = s.charAt(i-1) -'0'; 
        }

        //dp[1] = dp[1] + dp[0] 을 만족해야 함으로 dp[0]에 1을 초기값으로 줌.
        dp[0] = 1;
        for(int i = 1; i<= n; i++){
            if(arr[i] != 0){
                dp[i] = (dp[i] + dp[i-1])%MOD;
            }

            //2자리수 판별 
            int k = arr[i] + arr[i-1]*10;
            if(k >=10 && k <=26){
                dp[i] = (dp[i] + dp[i-2]) % MOD;
            }
        }

        System.out.println(dp[n]);


    }
    
   

}