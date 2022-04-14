
/*
블로그 참조 : https://lovelyunsh.tistory.com/55
*/
import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	static int N, M, F;
	static int map[][];
	static Point me, goal;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		me = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for (int i = 2; i < M + 2; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int gx = Integer.parseInt(st.nextToken());
			int gy = Integer.parseInt(st.nextToken());
			map[x][y] = gx * 100 + gy;
		}

		int people = 0;
		boolean flag = true;
		while (true) {
			int num = findSome();
			if (num == -1) {
				flag = false;
				break;
			}
			goal = new Point(num / 100, num % 100);

			if (!findGoal()) {
				flag = false;
				break;
			}
			people++;
			if (people == M)
				break;
		}
		System.out.println(flag ? F : -1);

	}

	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };

	static int findSome() {
		int dist = 0;
		Queue<Point> que = new LinkedList<Point>();
		que.offer(new Point(me.x, me.y));
		boolean visited[][] = new boolean[N + 1][N + 1];
		visited[me.x][me.y] = true;
		Point man = null;
		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				Point now = que.poll();
				if (map[now.x][now.y] > 1) {
					if (man == null) {
						man = new Point(now.x, now.y);
					} else {
						if (now.x < man.x) {
							man = new Point(now.x, now.y);
						} else if (now.x == man.x && now.y < man.y) {
							man = new Point(now.x, now.y);
						}
					}
				}
				for (int i = 0; i < 4; i++) {
					int row = now.x + dr[i];
					int col = now.y + dc[i];
					if (row <= 0 || col <= 0 || row > N || col > N)
						continue;
					if (visited[row][col])
						continue;
					if (map[row][col] == 1)
						continue;
					visited[row][col] = true;
					que.offer(new Point(row, col));
				}
			}
			if (man != null) {
				F -= dist;
				if (F < 0)
					return -1;
				me.x = man.x;
				me.y = man.y;
				int thenum = map[man.x][man.y];
				map[man.x][man.y] = 0;
				return thenum;
			}
			dist++;
		}
		return -1;
	}

	static boolean findGoal() {
		int dist = 0;
		Queue<Point> que = new LinkedList<Point>();
		que.offer(new Point(me.x, me.y));
		boolean visited[][] = new boolean[N + 1][N + 1];
		visited[me.x][me.y] = true;
		while (!que.isEmpty()) {
			int size = que.size();
			for (int s = 0; s < size; s++) {
				Point now = que.poll();
				if (goal.x == now.x && goal.y == now.y) {
					F -= dist;
					if (F < 0)
						return false;
					me.x = now.x;
					me.y = now.y;
					F += dist * 2;
					return true;
				}

				for (int i = 0; i < 4; i++) {
					int row = now.x + dr[i];
					int col = now.y + dc[i];
					if (row <= 0 || col <= 0 || row > N || col > N)
						continue;
					if (visited[row][col])
						continue;
					if (map[row][col] == 1)
						continue;
					visited[row][col] = true;
					que.offer(new Point(row, col));
				}

			}
			dist++;

		}
		return false;
	}

}
