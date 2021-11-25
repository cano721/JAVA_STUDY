package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 세준이는 체력 100을 가지고 사람들에게 인사를 다니려고 한다.
 * 사람들을 만날때마다 체력이 깍이는 대신 기쁨을 얻는다.
 * 대신 세준이가 죽으면 그동안 느낀 기쁨이 사라져 0이 된다.
 * 세준이가 사람들을 만나 느낄 수 있는 기쁨의 최댓값을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 2중 배열을 사용하여 한 개를 체력, 한 개를 현재 방문한 사람으로 설정한다.
	 * 배열을 넘어가면 세준이가 죽은 것이므로 넘어가고, 사람들을 만날 때마다 기쁨의 최댓값을 저장한다.
	 * 이제 마지막 사람을 만난후 남은 체력을 전부 돌며 기쁨의 최댓값을 찾는다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] status = new int[2][n + 1];
		for (int i = 0; i < status.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++)
				status[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[100][n + 1];
		dp[99][0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < dp.length; j++) {
				if (dp[j][i - 1] == 0)
					continue;
				try {
					dp[j][i] = dp[j][i - 1];
					dp[j - status[0][i]][i] = Math.max(dp[j - status[0][i]][i], dp[j][i - 1] + status[1][i]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		int max = 0;
		for (int i = 0; i < dp.length; i++)
			max = Math.max(dp[i][n], max);
		System.out.println(max - 1);
	}
}