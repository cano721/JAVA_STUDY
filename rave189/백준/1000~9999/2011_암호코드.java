package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int mod = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] arr = new int[input.length() + 1];
		for (int i = 1; i < arr.length; i++)
			arr[i] = input.charAt(i - 1) - '0';
		int[] dp = new int[arr.length];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			if (arr[i] != 0)
				dp[i] = dp[i - 1] % mod;
			int value = arr[i - 1] * 10 + arr[i];
			if (10 <= value && value <= 26)
				dp[i] = (dp[i - 2] + dp[i]) % mod;
		}
		System.out.println(dp[dp.length - 1] % mod);
	}
}