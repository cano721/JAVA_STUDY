package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * N������ �Ұ� �ִ�.
 * ���忡�� ���� �������� ������ �������� �ִ�.
 * ���� ���������� N������ �Ұ� �ְ�, ������ �������� N������ �Ұ� �ִ�.
 * �� ������ �Ҵ� �ش� ���������� �� �������� �����ϰ� �ִ�.
 * ���� �������� i�� ���� ������ �������� j�� ���� |i-j| <= 4��� ģ�� �����̴�.
 * ���� �������� ������ �������� �Ҹ� �̾��� Ⱦ�ܺ����� ��ġ�Ϸ��� �Ѵ�.
 * ��ġ�� �� �ִ� �ִ� ������ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * i��° ���� j��° �� ���̿� Ⱦ�ܺ����� ��ġ�� �� �ִ� ������ dp�� �����Ѵ�.
	 * ���� ��ģ�� ���̶�� dp[i-1][j]�� dp[i][j-1]�߿� ū ���� ����.
	 * ���� ģ�� ���̶�� dp[i][j]�� dp[i-1][j-1]�߿� ū ���� ����.
	 * 
	 * �ٵ� �ָ���?
	 * �� �����ϸ鼭 ���� �����ôµ� �׳� ������
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] left = new int[n + 1];
		int[] right = new int[n + 1];
		for (int i = 1; i <= n; i++)
			left[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++)
			right[i] = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (Math.abs(left[i] - right[j]) > 4)
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				else
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
			}
		}
		System.out.println(dp[n][n]);
	}
}