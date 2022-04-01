import java.util.*;
import java.io.*;

/**
 * 1. 앱 메모리와 비용(n개)과 구해야할 메모리(m)가 주어짐.
 * 
 * 2. dp[앱][최소비용] = 최대메모리
 * 
 * 3. 2중 포문으로 돌면서 m이상 중 최소비용 저장해두기
 * 
 */

public class 앱 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n];
        int[] cost = new int[n];

        int result = 10_000;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][10_001];
        

        for(int i = 0; i < n; i++){
            int curM = memory[i];
            int curC = cost[i];
            
            for(int j = 0; j <= 10_000; j++){
                if(i == 0){ // 첫번째 앱일때 비용체크
                    if(j >= curC){
                        dp[i][j] = curM;
                    }
                }else{
                    dp[i][j] = dp[i-1][j];
                    
                    if(j >= curC){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j-curC]+curM);
                    }
                }

                if(dp[i][j] >= m){
                    result = Math.min(j,result);
                }
            }
        }

        System.out.println(result);
    }
}
