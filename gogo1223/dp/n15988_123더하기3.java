package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class n15988_123더하기3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		/*
		 * type int[]면 합할때 오류 발생
		 * long 변수 선언해서 하나씩 더하던가
		 * long[]타입으로 바꿔준다.*/
		long[] dp = new long[max+1]; 
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= max; i++) {
			/* long 변수 선언해서 하나씩 더하기 (for문 사용도 가능)
			 * long sum = dp[i-1];
			 * sum += dp[i-2];
			 * sum += dp[i-3];*/
			dp[i] = (dp[i-1] + dp[i-2] + dp[i-3])%1000000009;
		}
		for (int i = 0; i < n; i++) {
			bw.write(dp[arr[i]]+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
