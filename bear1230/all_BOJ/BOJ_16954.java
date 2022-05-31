import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[][] arr = new char[8][8];
		int[] dx = { 0, 1, 0, -1, 0, 1, -1, -1, 1 };
		int[] dy = { 0, 0, 1, 0, -1, 1, 1, -1, -1 };
		for (int i = 0; i < 8; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		Queue<int[]> que = new ArrayDeque<>();
		que.offer(new int[] { 7, 0 });
		int cnt = que.size();

		while (!que.isEmpty()) {
			int[] x = que.poll();
			cnt--;
			if (arr[x[0]][x[1]] == '#')
				continue;

			for (int i = 0; i < 9; i++) {
				int nx = x[0] + dx[i];
				int ny = x[1] + dy[i];

				if (nx < 0 || nx >= 8 || ny < 0 || ny >= 8 || arr[nx][ny] == '#')
					continue;
				if (nx == 0 && ny == 7) {
					System.out.print(1);
					return;
				}
				que.offer(new int[] { nx, ny });
			}


			if (cnt > 0)
				continue;
			cnt = que.size();
			for (int i = 7; i >= 0; i--) {
				for (int j = 7; j >= 0; j--) {
					if (i - 1 < 0)
						arr[i][j] = '.';
					else
						arr[i][j] = arr[i - 1][j];
				}
			}
		}
		System.out.print(0);
	}
}