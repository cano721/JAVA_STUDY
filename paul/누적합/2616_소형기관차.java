import java.io.*;
import java.util.*;

public class Main{

    static int n, k;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[4][n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i+1] = arr[i] + Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());
        
        for(int i =1; i<=3; i++){
            for(int j = k; j<= n; j++){
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-k] + (arr[j] - arr[j-k]));
            }
        }
        System.out.println(dp[3][n]);

    }
}