package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N���� ���� �������� �����Ϸ��� �Ѵ�.
 * �� �������� ������ 0 �̻� M���Ϸ� ������ �� �ִ�.
 * �������� ���� �����ϱ� �� ������ Ű��ų� ���� �� �ִ�.
 * ������ ������ V + arr[i] �Ǵ� V - arr[i]�θ� ������ �� �ִ�.
 * �� �� ������ ���� ������ �ִ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * 2���� dp�� ����Ͽ� i��° ��� ���� �� �ִ� ������ �������� i+1�� �����Ѵ�.
	 * 
	 * 1���� dp�� ����ؼ� Ǯ����� ��.
	 * �ٵ� 1���� dp�δ� 5���� +1, -1�� ���� 4�� 6�� ������Ʈ���־��� ��, 6�� ������ ������ �� �ִ� ���� ��츦
	 * ���� ���Ѵ�. (+�� ����)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] music = new int[n];
		for (int i = 0; i < n; i++)
			music[i] = Integer.parseInt(st.nextToken());
		int[][] dp = new int[n + 1][m + 1];
		dp[0][s]++;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				if (dp[i][j] == 0)
					continue;
				if (j + music[i] <= m)
					dp[i + 1][j + music[i]] = 1;
				if (j - music[i] >= 0)
					dp[i + 1][j - music[i]] = 1;
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[n][i] != 0) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
}