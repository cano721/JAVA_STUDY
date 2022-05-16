import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int[][] dp = new int[str.length() + 1][str.length() + 1];
		for (int i = 2; i <= str.length(); i++) {
			for (int j = 0; j <= str.length() - i; j++) {
				int end = j + i;

				for (int k = j + 1; k < end; k++) {
					dp[j][end] = Math.max(dp[j][end], dp[j][k] + dp[k][end]);
				}

				if (str.charAt(j) == 'a' && str.charAt(end - 1) == 't'
						|| str.charAt(j) == 'g' && str.charAt(end - 1) == 'c')
					dp[j][end] = Math.max(dp[j][end], dp[j + 1][end - 1] + 2);
			}
		}

		System.out.println(dp[0][str.length()]);
	}
}