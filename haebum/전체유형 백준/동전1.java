
/**
 * 1. n개의 동전 종류를 이용해 k원 맞추기
 * 
 * 2. 모든 경우의 수 출력
 * 
 * 3. 완탐은 불가능하므로 DP 사용
 * 
 * 4. DP[가치] = 경우의 수
 * 
 * 5. 2중 포문으로 동전과 가치를 돌려,
 *    각 동전마다 가치에 나올 수 있는 경우의수를 구하고
 *    더해가며 진행
 * 
 * 6. 가치 다음에 동전을 돌리면 중복이 존재함..
 */

import java.util.*;
import java.io.*;

public class 동전1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());    

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coin = new int[n];

        for(int i = 0; i < n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];

        dp[0] = 1;

        for(int i = 0; i < n; i++){
            int v = coin[i];
            for(int j = v; j <= k; j++){
                dp[j] += dp[j-v];
            }
        }
        System.out.println(dp[k]);
    }
}
