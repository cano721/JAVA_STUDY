import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //물품의 수
        int k = Integer.parseInt(st.nextToken());// 최대 무게
        
        int w[] = new int[n+1]; //물건의 무게
        int v[] = new int [n+1]; // 물건의 가치
        
        int dp[][] = new int[n+1][k+1];
        for(int i =1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        for(int i =1; i<=n; i++){
            for(int j=1; j<=k; j++){
                if(j< w[i]){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
                }
            }
            
        }
        System.out.println(dp[n][k]);
    }
}