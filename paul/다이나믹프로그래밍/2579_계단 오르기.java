import java.util.*;
import java.io.*;
/**
 *  테이블 정의 : dp[i][n] = k : i번 계단칸을 연속으로 n개 올랐을 때의 경우의 수는 k이다.
 *             dp[i][0] : 연속으로 0개를 올랐다. 즉, 이전의 계단은 밟지 않은 경우를 의미.
 *             dp[i][1] : 연속으로 1개 밟았다. --> 이전이 계단 반드시 밟은 경우
 *  점화식 : dp[i][0] = max(dp[i-2][0] , dp[i-2][1]) + value[i] // 
 *          >> 이전의 계단은 밟지 않았을 때 최대가 되는 경우는 2번째 칸을 밟을 모든 경우의 수 중 큰 값이 최대가 된다.
 *         dp[i][1] = dp[i-1][0] + value[i]
 *          >> 이전의 계단을 밟았고 현재 계단을 밟는 경우는 i-1번째 계단을 연속해서 0번 밟은 경우의 수에서 value값을 더한 것임
 */
public class Main {

    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       int[] arr = new int[n+1];
       int[][] dp = new int[n+1][2];
       for(int i =1; i<=n; i++){
            arr[i]  = Integer.parseInt(br.readLine());
       }

       dp[1][0] = arr[1];
       dp[1][1] = 0;

       for(int i =2; i<=n; i++){
           dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
           dp[i][1] = dp[i-1][0] + arr[i];
       }

       System.out.println(Math.max(dp[n][0], dp[n][1]));

    }

}

