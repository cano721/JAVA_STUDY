package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * NxM ũ���� ���簢���� �־�����.
 * �� ���簢���� �� ���� ���簢������ ������.
 * ���簢���� ���ο��� 1x1 ũ�⸶�� ���ڰ� �����ִ�.
 * �� ���� ���簢���� ������ ������ ���� ���� ��
 * ��� ������ ���� �ִ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * �ִ��� ���Ϸ��� ������ ���簢���� �� ä���� �Ѵ�.
	 * ���簢���� �� ���� ���簢������ ������ ������� 6������ �ִ�.
	 * ��� ��츦 �� ���غ��� ������ ���� �� ����
	 * 
	 * ������ ���� int�ε� ���� ����� long�� �� �־ ���� long���� �ٲ� (�̰Ŷ����� 30�� ��� ��)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long[][] map = new long[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			for (int j = 1; j <= m; j++)
				map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + (input.charAt(j - 1) - '0');
		}
		long answer = 0;
		for (int i = 1; i < n; i++)
			for (int j = i + 1; j < n; j++)
				answer = max(answer, map[i][m] * (map[j][m] - map[i][m]) * (map[n][m] - map[j][m]));
		for (int i = 1; i < m; i++)
			for (int j = i + 1; j < m; j++)
				answer = max(answer, map[n][i] * (map[n][j] - map[n][i]) * (map[n][m] - map[n][j]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[i][j] * (map[n][j] - map[i][j]) * (map[n][m] - map[n][j]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[n][j] * (map[i][m] - map[i][j]) * (map[n][m] - map[i][m] - map[n][j] + map[i][j]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[i][j] * (map[i][m] - map[i][j]) * (map[n][m] - map[i][m]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[i][m] * (map[n][j] - map[i][j]) * (map[n][m] - map[n][j] - map[i][m] + map[i][j]));
		System.out.println(answer);
	}

	public static long max(long a, long b) {
		return a > b ? a : b;
	}
}