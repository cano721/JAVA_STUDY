package BOJ;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N+1];
		int[] sum = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + arr[i];
		}
		
		int K = Integer.parseInt(br.readLine());
		
		
		
		
		int[][] DP = new int[4][N+1];
		
		for(int i = 1; i < 4; i++) {
			for(int j = i*K; j <= N; j++) {
				DP[i][j] = Math.max(DP[i][j-1], DP[i-1][j-K] + sum[j] - sum[j-K]);
			}
		}
		
		System.out.println(DP[3][N]);
		}
}