import java.util.*;
import java.io.*;
/**
 *  테이블 정의 : dp[i][j] = k , i번 째 포도주잔을 연속으로 j잔 먹었을 때의 최대값.
 *             j = 0 (현재 포도잔 안먹음), j = 1 (현재 포도잔 먹음.. 이전 x), j= 2(현재 포도잔 먹고 이전것도 먹음)
 *  점화식 : dp[i][0] = max(dp[i-1][0], dp[i-1][1] ,dp[i-1][2]) 이전 포도잔 결과에서의 최대값.
 *         dp[i][1] = dp[i-1][0] + arr[i] // 연속으로 한잔 마심, 즉 이전 포도잔은 먹지 않은 것.
 *         dp[i][2] = dp[i-1]dp[1] +arr[i] //연속으로 두잔 마심   
 */
public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        for(int i =1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n+1][3];
        dp[1][0] =0; dp[1][1] = arr[1]; dp[1][2] = 0;
   
        for(int i =2; i<=n; i++){

            //1. 현재 주스 안먹음
            dp[i][0] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]),dp[i-1][0]);

            //2. 이전꺼 안먹고 현재 주스 먹음 
            dp[i][1] = dp[i-1][0] + arr[i];

            //3. 이전꺼 먹고 현재 주스 먹음
            dp[i][2] = dp[i-1][1] + arr[i];
        }
        
        int ans =0;
        for(int i =0; i<3; i++){
            ans = Math.max(ans, dp[n][i]);
        }

        System.out.println(ans);

    }
}

