import java.io.*;

public class Main {

	static int n, k;
	static int[] coin, dp;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		k = Integer.parseInt(input[1]);

		coin = new int[n];
		dp = new int[k + 1];

		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j >= coin[i])
					dp[j] += dp[j - coin[i]];
			}
		}

		System.out.println(dp[k]);
	}
}