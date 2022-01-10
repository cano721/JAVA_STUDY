package cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 * dp[기관차운영수][i번째객차] = 최대 운송가능한 사람 수
 * 
 * N번째 객차에 해당하는 기관차의 손님수 S[N]−S[N−K]
 * 
 * DP[N][M]=max(DP[N][M−1],DP[N−1][M−K]+S[M]−S[M−K])
 * 
 * 
 @author Jeeyani
 */

public class BJ2616_소형기관차 {

	static int n, k;
	static int[] train;
	static int[] passagierSum;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		train = new int[n+1];
		passagierSum = new int[n+1];
		dp = new int[4][n+1];

		for (int i = 1; i <=n; i++) {
			train[i] = Integer.parseInt(st.nextToken());
			passagierSum[i] = passagierSum[i - 1] + train[i];
		}

		k = Integer.parseInt(br.readLine());
		
		int maxResult = getPassagierMax();
		sb.append(maxResult + "\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static int getPassagierMax() {

		for (int i = 1; i < 4; i++) {
			for (int j = i*k; j <=n; j++) {
				
				if(i==1) {
					dp[i][j] = Math.max(dp[i][j-1], passagierSum[j]-passagierSum[j-k]);
				}
				else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-k]+passagierSum[j]-passagierSum[j-k]);
				}

			}
		}
		
		return dp[3][n];
	}

}
