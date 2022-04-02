package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon21611_마법사_상어와_블리자드 {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static int dx[] = { 0, -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		int map[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		LinkedList<Integer> left = new LinkedList<Integer>();
		LinkedList<Integer> down = new LinkedList<Integer>();
		LinkedList<Integer> right = new LinkedList<Integer>();
		LinkedList<Integer> up = new LinkedList<Integer>();
		int temp = 0;
		int tempPlus = 2;
		while (temp < N * N) {
			left.add(temp);
			temp += tempPlus;
			down.add(temp);
			temp += tempPlus;
			right.add(temp);
			temp += tempPlus;
			up.add(temp);
			temp += tempPlus + 1;
			tempPlus += 2;
		}
		LinkedList<Integer> ball = new LinkedList<Integer>();
		int cnt = 1;
		int x = (N + 1) / 2;
		int y = (N + 1) / 2;
		outer: while (true) {
			for (int i = 0; i < cnt; i++) {
				ball.add(map[x][--y]);
				if (map[x][y] == 0) {
					break outer;
				}
			}
			for (int i = 0; i < cnt; i++) {
				ball.add(map[++x][y]);
				if (map[x][y] == 0) {
					break outer;
				}
			}
			cnt++;
			for (int i = 0; i < cnt; i++) {
				ball.add(map[x][++y]);
				if (map[x][y] == 0) {
					break outer;
				}
			}
			for (int i = 0; i < cnt; i++) {
				ball.add(map[--x][y]);
				if (map[x][y] == 0) {
					break outer;
				}
			}
			cnt++;
		}
		ball.pollLast();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if (d == 1) {
				for (int j = 0; j < s; j++) {
					ball.remove(up.get(j) - j);
				}
			} else if (d == 2) {
				for (int j = 0; j < s; j++) {
					ball.remove(down.get(j) - j);
				}
			} else if (d == 3) {
				for (int j = 0; j < s; j++) {
					ball.remove(left.get(j) - j);
				}
			} else {
				for (int j = 0; j < s; j++) {
					ball.remove(right.get(j) - j);
				}
			}
			while (check(ball)) {
				int num = ball.poll();
				int count = 1;
				int size = ball.size();
				for (int j = 0; j < size; j++) {
					int nextNum = ball.poll();
					if (nextNum == num) {
						count++;
					} else if (count >= 4) {
						answer += num * count;
						num = nextNum;
						count = 1;
					} else {
						for (int k = 0; k < count; k++) {
							ball.add(num);
						}
						num = nextNum;
						count = 1;
					}
				}
				if (count < 4) {
					for (int k = 0; k < count; k++) {
						ball.add(num);
					}
				}
			}
			int size = ball.size();
			int num = ball.poll();
			int count = 1;
			for (int j = 1; j < size; j++) {
				int tempNum = ball.poll();
				if (tempNum == num) {
					count++;
				} else {
					ball.add(count);
					ball.add(num);
					num = tempNum;
					count = 1;
				}
			}
			ball.add(count);
			ball.add(num);
		}
		System.out.println(answer);
	}

	private static boolean check(LinkedList<Integer> ball) {
		int num = ball.get(0);
		int cnt = 1;
		for (int i = 1; i < ball.size(); i++) {
			if (ball.get(i) == num) {
				cnt++;
			} else {
				num = ball.get(i);
				cnt = 1;
			}
			if (cnt >= 4) {
				return true;
			}
		}
		return false;
	}
}
