package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10971_외판원순회2 {

	static int n;
	static int[][] W;
	static boolean[] visit;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		W = new int[n][n];
		visit = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < n; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		getCourses(0, 0);
		System.out.println(min);
	}

	private static void getCourses(int idx, int cnt) {

		//모든 경로를 다 돌았다면 종료
		if (cnt == n) {
			getMin();
			return;
		}

		for (int i = idx; i < n; i++) {

			if (!visit[i]) {
				visit[i] = true;
				getCourses(i + 1, cnt + 1);

				visit[i] = false; // 재귀가 끝나면 비방문으로 변경
			}

		}

	}

	private static void getMin() {
		int course = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {

				if (visit[i] == true && visit[j] == true) {
					course += W[i][j];
				}
			}
		}

		min = Math.min(min, course);

	}

}
