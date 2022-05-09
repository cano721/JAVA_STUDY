package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * RxC �迭�� �־�����.
 * 4���� �� (r1, c1), (r2, c2)�� �־��� ��
 * �ش� ������ ��� ���� ���ϴ� ����
 * �Ҽ����� ������.
 * @author Rave
 *
 */
public class Main {

	/**
	 * ���� ������ Ǫ�� ����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[][] map = new int[r + 1][c + 1];
		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= c; j++)
				map[i][j] = map[i][j - 1] + map[i - 1][j] + Integer.parseInt(st.nextToken()) - map[i - 1][j - 1];
		}
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int sum = map[r2][c2] - map[r2][c1 - 1] - map[r1 - 1][c2] + map[r1 - 1][c1 - 1];
			int count = (r2 - r1 + 1) * (c2 - c1 + 1);
			answer.append(sum / count).append('\n');
		}
		System.out.print(answer);
	}
}