package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �����̴� ü�� 100�� ������ ����鿡�� �λ縦 �ٴϷ��� �Ѵ�.
 * ������� ���������� ü���� ���̴� ��� ����� ��´�.
 * ��� �����̰� ������ �׵��� ���� ����� ����� 0�� �ȴ�.
 * �����̰� ������� ���� ���� �� �ִ� ����� �ִ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * 2�� �迭�� ����Ͽ� �� ���� ü��, �� ���� ���� �湮�� ������� �����Ѵ�.
	 * �迭�� �Ѿ�� �����̰� ���� ���̹Ƿ� �Ѿ��, ������� ���� ������ ����� �ִ��� �����Ѵ�.
	 * ���� ������ ����� ������ ���� ü���� ���� ���� ����� �ִ��� ã�´�.
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