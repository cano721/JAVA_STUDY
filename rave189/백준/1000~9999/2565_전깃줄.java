package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PowerPole[] poles = new PowerPole[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			poles[i] = new PowerPole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(poles);
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++)
				if (poles[j].y < poles[i].y)
					dp[i] = Math.max(dp[i], dp[j] + 1);
		}
		int answer = 0;
		for(int v : dp)
			answer = Math.max(answer, v);
		System.out.println(n-answer);
	}
}

class PowerPole implements Comparable<PowerPole> {
	int x, y;

	public PowerPole(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(PowerPole o) {
		return x - o.x;
	}
}