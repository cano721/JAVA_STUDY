package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int l, r;
	static Queue<Integer> unionPeople = new LinkedList<>();
	static Queue<ArrayList<Point>> union = new LinkedList<>();
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		while (isMove()) {
			move();
			answer++;
		}
		System.out.println(answer);
	}

	public static boolean isMove() {
		visited = new boolean[map.length][map[0].length];
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				if (!visited[i][j])
					findCountry(new Point(i, j));
		return !union.isEmpty();
	}

	public static void findCountry(Point start) {
		Queue<Point> q = new LinkedList<>();
		ArrayList<Point> people = new ArrayList<>();
		q.add(start);
		people.add(start);
		visited[start.x][start.y] = true;
		int sum = 0;
		int count = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			sum += map[cur.x][cur.y];
			count++;
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || !isValid(Math.abs(map[nextX][nextY] - map[cur.x][cur.y])))
						continue;
					Point next = new Point(nextX, nextY);
					q.add(next);
					people.add(next);
					visited[nextX][nextY] = true;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		if (count != 1) {
			unionPeople.add(sum / count);
			union.add(people);
		}
	}

	public static boolean isValid(int sub) {
		return l <= sub && sub <= r;
	}

	public static void move() {
		while (!union.isEmpty()) {
			int ave = unionPeople.poll();
			for (Point country : union.poll())
				map[country.x][country.y] = ave;
		}
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}