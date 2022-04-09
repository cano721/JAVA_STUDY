
/*
첫째 줄에 격자판의 크기 R, C와 상어의 수 M이 주어진다. (2 ≤ R, C ≤ 100, 0 ≤ M ≤ R×C)

둘째 줄부터 M개의 줄에 상어의 정보가 주어진다. 상어의 정보는 다섯 정수 r, c, s, d, z 
(1 ≤ r ≤ R, 1 ≤ c ≤ C, 0 ≤ s ≤ 1000, 1 ≤ d ≤ 4, 1 ≤ z ≤ 10000) 로 이루어져 있다. 
(r, c)는 상어의 위치, s는 속력, d는 이동 방향, z는 크기이다. 
d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미한다.

두 상어가 같은 크기를 갖는 경우는 없고, 하나의 칸에 둘 이상의 상어가 있는 경우는 없다.
*/

import java.io.*;
import java.util.*;

public class Main {
	static int R, C, M, king;
	static int map[][];
	static Shark[] sharks;
	static int[] dr = { -1, 0, 0, 1 }, dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[R + 1][C + 1];
		sharks = new Shark[M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if (d == 1)
				d = 0;
			else if (d == 2)
				d = 3;
			else if (d == 3)
				d = 2;
			else
				d = 1;

			map[r][c] = i;
			sharks[i] = new Shark(r, c, s, d, z);
		}

		System.out.println(sol());
	}

	private static int sol() {

		int res = 0;
		while (king++ < C) {
			res += fishing();
			move();
		}

		return res;
	}

	private static void move() {

		map = new int[R + 1][C + 1];

		for (int i = 1; i <= M; i++) {
			if (sharks[i] == null)
				continue;

			Shark now = sharks[i];
			int turn = 1, r = now.r, c = now.c;
			for (int m = 0; m < now.s; m++) {
				r += turn * dr[now.d];
				c += turn * dc[now.d];
				if (r < 1 || r > R || c < 1 || c > C) {
					// 방향 반대
					turn *= -1;
					// 이전 칸 이동
					r += (turn * dr[now.d]) * 2;
					c += (turn * dc[now.d]) * 2;
				}
			}

			if (turn == -1) {
				sharks[i].d = 3 - sharks[i].d;
			}

			if (map[r][c] > 0) {
				if (sharks[map[r][c]].z > now.z)
					sharks[i] = null;
				else {
					sharks[i].r = r;
					sharks[i].c = c;
					sharks[map[r][c]] = null;
					map[r][c] = i;
				}
			} else {
				sharks[i].r = r;
				sharks[i].c = c;
				map[r][c] = i;
			}
		}
	}

	private static int fishing() {
		for (int i = 1; i <= R; i++) {
			if (map[i][king] > 0) {
				int size = sharks[map[i][king]].z;
				sharks[map[i][king]] = null;
				map[i][king] = 0;

				return size;
			}
		}

		return 0;
	}

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}
}
