package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon2011_암호코드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pw = br.readLine();
		int n = pw.length();
		int[] dp = new int[n + 1];
		char[] ch = new char[n + 1];
		for (int i = 1; i <= n; i++) {
			ch[i] = pw.charAt(i - 1);
		}

		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			// [i]가 알파벳 하나인 경우
			int num = ch[i] - '0';
			if (1 <= num && num <= 9) {
				dp[i] = dp[i - 1] % 1000000;
			}
			if (i == 1)
				continue;

			// [i-1]+[i]가 알파벳 하나인 경우. 10~26
			num = (ch[i - 1] - '0') * 10 + (ch[i] - '0');
			if (10 <= num && num <= 26) {
				dp[i] = (dp[i] + dp[i - 2]) % 1000000;
			}
		}
		System.out.println(dp[n]);
	}
}