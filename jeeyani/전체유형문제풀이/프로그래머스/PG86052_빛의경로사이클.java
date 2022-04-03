package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*

1. 각 정점이 상하좌우로 방문했는지 여부 체크 (3차원배열)

ex) "SR" "LR"
        0        1
 0   [f,f,f,f] [f,f,f,f]
 1   [f,f,f,f] [f,f,f,f]
 
2. 하좌상우 방향으로 방향 전환
3. 방문하지 않은 곳은 경로 구해주기
    3-1. 방문처리하면서, 방문하지 않은 방향으로 계속 이동하기
    3-2. 배열 밖으로 나간 경우, 반대편으로 이동시켜주기 (r+R길이) % R

*/

public class PG86052_빛의경로사이클 {

	public static void main(String[] args) {

		String[] grid = { "SR", "RL" };

		int[] result = solution(grid);

		for (int res : result) {
			System.out.println(res);
		}

	}

	static int R, C;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static boolean[][][] visited;

	private static int[] solution(String[] grid) {
		ArrayList<Integer> answer = new ArrayList<>();

		R = grid.length;
		C = grid[0].length();

		visited = new boolean[R][C][4];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = 0; k < 4; k++) {

					if (!visited[i][j][k]) {
						int cnt = lightRoute(grid, i, j, k);
						answer.add(cnt);
					}

				}
			}
		}

		return answer.stream().sorted().mapToInt(i -> i).toArray();
	}

	private static int lightRoute(String[] grid, int r, int c, int d) {
		int cnt = 0;

		while (true) {

			if (visited[r][c][d]) break;

			cnt++;
			visited[r][c][d] = true;

			//좌회전
			if (grid[r].charAt(c) == 'L') {
				d = (d == 0) ? 3 : d - 1;
			}
			//우회전
			else if (grid[r].charAt(c) == 'R') {
				d = (d == 3) ? 0 : d + 1;
			}

			r = (r + dr[d] + R) % R;
			c = (c + dc[d] + C) % C;
		}

		return cnt;
	}

}