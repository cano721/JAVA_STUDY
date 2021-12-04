import java.util.*;
import java.io.*;
/**
 *  테이블 정의 : dp[i][j] = k, i번 째 음악에서 j 볼륨이 가능하면 1, 그렇지 않다면 0.
 *  점화식 : dp[i][j] = max(dp[i-1][j-arr[i]], dp[i-1][j+arr[i]]) 
 *         i번째의 음악의 j볼륨은 i-1의 볼륨에서 +arr[i] 한것 혹은 -arr[i] 한 것에서 온 것이다.
 *          이때 주의할 점은 j-arr[i], j+arr[i] 가 0부터 m까지의 범위에 포함되어야함.
 *          범위를 벗아나면 가능하지 않으므로 각각 따로 구해줘야한다.
 *  최조
 */
public class Main {
    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int n = Integer.parseInt(st.nextToken());
       int s = Integer.parseInt(st.nextToken());
       int m = Integer.parseInt(st.nextToken());
       int[] arr = new int[n+1];

       st = new StringTokenizer(br.readLine());
       for(int i =1; i<=n ;i++){
           arr[i] = Integer.parseInt(st.nextToken());
       }

       int[][] dp = new int[n+1][m+1];

       dp[0][s] = 1;

       for(int i = 1; i<=n; i++){
           for(int j= m; j>=0; j--){
               if(j  >= arr[i]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i]]);
               if(j +arr[i] <= m ) dp[i][j] = Math.max(dp[i][j], dp[i-1][j+arr[i]]);
           }
       }

       int ans = -1;
       for(int i = m; i>=0 ; i--){
           if(dp[n][i] == 0) continue;
           ans = i;
           break;
       }
       System.out.println(ans);
    }
    
}
