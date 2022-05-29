package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjoon1915_가장_큰_정사각형 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String[] input = br.readLine().split("");

			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(input[j - 1]);
			}
		}

		// (0, 0)부터 (i, j)까지의 부분 합
		int[][] psum = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				psum[i][j] = psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1] + map[i][j];
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				// 게임판의 숫자가 1이라면,
				if (map[i][j] == 1) {
					int res = 1;

					int idx = 1;

					// 2 x 2, 3 x 3, ... 사각형이 가능한지 탐색함.
					while (i + idx <= N && j + idx <= M) {
						// 특정 두 지점 사이의 부분 합
						int space = psum[i + idx][j + idx] - psum[i + idx][j - 1] - psum[i - 1][j + idx]
								+ psum[i - 1][j - 1];

						idx++;

						if (space != idx * idx) {
							break;
						}

						res = idx * idx;
					}

					ans = Math.max(ans, res);
				}
			}
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}