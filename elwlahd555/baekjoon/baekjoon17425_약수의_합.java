package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon17425_약수의_합 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		long dp[] = new long[1000001];
		long fx[] = new long[1000001];
		Arrays.fill(fx, 1);
		for (int i = 2; i < fx.length; i++) {
			for (int j = 1; i * j < fx.length; j++) {
				fx[i * j] += i;
			}
		}
		for (int i = 1; i < dp.length; i++) {
			dp[i] += dp[i - 1] + fx[i];
		}
		StringBuilder sb = new StringBuilder();
		while(T-- >0) {
			sb.append(dp[Integer.parseInt(br.readLine())]+"\n");
		}
		System.out.println(sb.toString());
	}
}
