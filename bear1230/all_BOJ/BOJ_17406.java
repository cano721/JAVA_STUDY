
// https://lovelyunsh.tistory.com/74 참조
import java.io.*;
import java.util.*;

public class Main {
	static class node {
		int r;
		int c;
		int s;

		public node(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	static node nodes[];
	static int N, M, K, map[][];
	static node sel[];
	static boolean visit[];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		sel = new node[K];
		visit = new boolean[K];
		nodes = new node[K];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			nodes[i] = new node(r, c, s);
		}

		func(0);
		System.out.println(min);
	}

	static void func(int selidx) {
		if (selidx == K) {
			int[][] copymap = new int[N][M];
			for (int i = 0; i < N; i++)
				copymap[i] = map[i].clone();
			for (int i = 0; i < K; i++)
				turn(copymap, sel[i]);
			min = Math.min(min, findmin(copymap));
			return;
		}
		for (int i = 0; i < K; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			sel[selidx] = nodes[i];
			func(selidx + 1);
			visit[i] = false;
		}

	}

	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	static void turn(int map[][], node t) {
		for (int i = 1; i <= t.s; i++) {
			int r = t.r - i;
			int c = t.c - i;
			int d = 0;
			int temp = map[r][c];
			while (true) {
				r = r + dr[d];
				c = c + dc[d];
				temp = map[r][c] ^ temp ^ (map[r][c] = temp);
				if (Math.abs(t.r - r) == i && Math.abs(t.c - c) == i) {
					if (++d >= 4)
						break;
				}
			}
		}
	}

	static int findmin(int map[][]) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++)
				sum += map[i][j];
			min = Math.min(sum, min);
		}

		return min;
	}
}