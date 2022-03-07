package elwlahd555.programmers;

import java.util.ArrayList;

public class 프로그래머스86052_빛의_경로_사이클 {
	public static void main(String[] args) {
		String grid[] = { "SL", "LR" };
		System.out.println(solution(grid));
	}

	static int R, C;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 }; // 아래, 왼, 위, 오른
	static boolean[][][] isVisited;

	public static int[] solution(String[] grid) {
		ArrayList<Integer> answer = new ArrayList<Integer>();

		R = grid.length;
		C = grid[0].length();

		isVisited = new boolean[R][C][4];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int d = 0; d < 4; d++) {
					if (!isVisited[i][j][d])
						answer.add(light(grid, i, j, d));
				}
			}
		}

		return answer.stream().sorted().mapToInt(i -> i).toArray();
	}

	private static int light(String[] grid, int r, int c, int d) {
		int cnt = 0; // 이동거리

		while (true) {
			if (isVisited[r][c][d])
				break;

			cnt++; // 거리증가
			isVisited[r][c][d] = true; // 방문처리

			if (grid[r].charAt(c) == 'L')
				d = d == 0 ? 3 : d - 1; // 좌회전
			else if (grid[r].charAt(c) == 'R')
				d = d == 3 ? 0 : d + 1; // 우회전

			r = (r + dr[d] + R) % R;
			c = (c + dc[d] + C) % C;
		}

		return cnt;
	}

}
