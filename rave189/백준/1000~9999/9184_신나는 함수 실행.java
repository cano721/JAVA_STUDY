package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ��� �Լ� w�� �־�����.
 * �׽�Ʈ ���̽��� �־����� a, b, c�� �־��� ��, w(a, b, c)�� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[][][] map;

	/**
	 * ��͸� ���� �����鼭 ��� ���� map�� �����صд�.
	 * ���� map�� �ִ� ���� ã������ �Ѵٸ� ���� �״�� ��ȯ���ش�.
	 * 
	 * 3�� for������ map�� ���� ���ϰ� �����ϴ°� �� ������.(map�� ũ�⸦ 21 21 21�� �ΰ�)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		map = new int[51][51][51];
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == -1 && b == -1 && c == -1)
				break;
			answer.append(String.format("w(%d, %d, %d) = %d", a, b, c, w(a, b, c))).append('\n');
		}
		System.out.println(answer);
	}

	public static int w(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;
		else if (a > 20 || b > 20 || c > 20)
			return map[a][b][c] = w(20, 20, 20);
		else if (map[a][b][c] != 0)
			return map[a][b][c];
		else if (a < b && b < c)
			return map[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		else
			return map[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
	}
}