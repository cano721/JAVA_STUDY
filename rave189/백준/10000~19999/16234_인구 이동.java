package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxN 지도가 주어진다. 지도의 1x1은 하나의 나라를 의미하며 그 나라의 인구가 입력으로 주어진다.
 * 이 나라들은 서로의 인구 차이가 L 이상 R 이하인 경우 국경을 열어 인구가 이동할 수 있게 한다.
 * 국경이 열린 나라들은 서로 연합으로 부르며, 인구가 이동하면 각 나라의 인구는
 * 연합의 인구수 / 연합에 들어온 나라의 개수로 변하게 된다.
 * 이 때, 인구 이동이 일어나는 횟수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static int l, r;
	static Queue<Integer> unionPeople = new LinkedList<>();
	static Queue<ArrayList<Point>> union = new LinkedList<>();
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * 연합인 나라들을 bfs를 통해 찾아내고, 나라들의 좌표는 union에, 인구 이동후 바뀌는 인구 수는 unionPeople에 저장한다.
	 * 이후 union에 있는 나라들의 값을 모두 unionPeople에 있는 값으로 변경한다.
	 * 530ms정도 소요.
	 * 연합인 나라들을 찾은 후 바로 인구를 변경해도 된다. (이미 visited가 true이므로 bfs가 탐색하지 않음)
	 * 이러면 array를 큐에 저장하지 않아도 됨. 시간의 차이는 있으려나..? 조금 있을 거 같긴 한데
	 */
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