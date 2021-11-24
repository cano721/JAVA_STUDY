package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 재귀 함수 w가 주어진다.
 * 테스트 케이스가 주어지고 a, b, c가 주어질 때, w(a, b, c)를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][][] map;

	/**
	 * 재귀를 직접 돌리면서 결과 값을 map에 저장해둔다.
	 * 만약 map에 있는 값을 찾으려고 한다면 값을 그대로 반환해준다.
	 * 
	 * 3중 for문으로 map을 먼저 구하고 시작하는게 더 빠르다.(map의 크기를 21 21 21로 두고)
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