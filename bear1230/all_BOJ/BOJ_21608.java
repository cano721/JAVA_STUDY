import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map;
	static int[][] list;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		list = new int[n * n + 1][4];
		for (int i = 0; i < n * n; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				list[number][j] = Integer.parseInt(st.nextToken());
			}
			Node node = calc_node(number);
			map[node.r][node.c] = number;
		}
		int ans = 0;
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int num = map[i][j];
				int[] like = list[num];
				for (int k = 0; k < 4; k++) {
					int newR = i + dx[k];
					int newC = j + dy[k];
					if (0 <= newR && newR < n && 0 <= newC && newC < n) {
						if (map[newR][newC] == like[0] || map[newR][newC] == like[1] || map[newR][newC] == like[2]
								|| map[newR][newC] == like[3]) {
							tmp++;
						}
					}
				}
				if (tmp == 1)
					ans += 1;
				else if (tmp == 2)
					ans += 10;
				else if (tmp == 3)
					ans += 100;
				else if (tmp == 4)
					ans += 1000;
				tmp = 0;
			}
		}

		System.out.println(ans);
	}

	public static int[] calc(int r, int c, int number) {
		int likes = 0;
		int empty = 0;
		int[] like = list[number];
		for (int i = 0; i < 4; i++) {
			int newR = r + dx[i];
			int newC = c + dy[i];
			if (0 <= newR && newR < n && 0 <= newC && newC < n) {
				if (map[newR][newC] == like[0] || map[newR][newC] == like[1] || map[newR][newC] == like[2]
						|| map[newR][newC] == like[3]) {
					likes++;
				} else if (map[newR][newC] == 0)
					empty++;
			}
		}
		return new int[] { likes, empty };
	}

	public static Node calc_node(int number) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] == 0) {
					int[] ans = calc(r, c, number);
					int likes = ans[0];
					int empty = ans[1];
					pq.add(new Node(r, c, likes, empty));
				}
			}
		}
		return pq.poll();
	}

	public static class Node implements Comparable<Node> {
		int r, c, likes, empty;

		public Node(int r, int c, int like, int empty) {
			this.r = r;
			this.c = c;
			this.likes = like;
			this.empty = empty;
		}

		@Override
		public int compareTo(Node o) {
			if (this.likes == o.likes) {
				if (this.empty == o.empty) {
					if (this.r == o.r)
						return this.c - o.c;
					else
						return this.r - o.r;
				} else
					return o.empty - this.empty;
			} else
				return o.likes - this.likes;
		}
	}
}