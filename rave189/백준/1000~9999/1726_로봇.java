package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 로봇이 있고, 로봇에게 명령을 내릴 수 있다.
 * 명령은 두 가지로 다음과 같다.
 * 1. 로봇이 현재 바라보는 방향으로 1, 2 또는 3 만큼 움직일 수 있다.
 * 2. 로봇의 방향을 왼쪽이나 오른쪽으로 바꾼다.
 * 로봇의 시작 상태와 도착 상태가 주어질 때, 도착 상태와 같아지기 위한 최소 명령 횟수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static Point start, end;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	/**
	 * bfs 문제
	 * 처음에는 문제에서 주어진대로 현재 위치에서 모든 turn 경우, 움직일 수 있는 모든 경우를 큐에 넣어주었다. (시간 초과, 메모리 초과, 틀렸습니다)
	 * 그래서 생각을 바꿔 명령과 상관없이 현재 로봇의 위치에서 4방향을 모두 검사한다.
	 * 4방향 중 갈 수 있는 방향이 있다면 현재 바라보는 위치에서 움직이고 싶은 위치로 방향 전환 명령 + 움직이는 명령을 더해주어 큐에 넣었다. (훨씬 빠름)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			if (i == 0)
				start = new Point(x, y, d, 0);
			else
				end = new Point(x, y, d, 0);
		}
		bfs();
	}

	public static void bfs() {
		PriorityQueue<Point> q = new PriorityQueue<>((v1, v2) -> v1.count - v2.count);
		q.add(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.equals(end)) {
				System.out.println(cur.count + getTurnCnt(cur.direction, end.direction));
				return;
			}
			for (int i = 0; i < dx.length; i++) {
				for (int step = 1; step <= 3; step++) {
					int nextX = cur.x + dx[i] * step;
					int nextY = cur.y + dy[i] * step;
					try {
						if (map[nextX][nextY] == 1)
							break;
						else if (visited[nextX][nextY])
							continue;
						visited[nextX][nextY] = true;
						q.add(new Point(nextX, nextY, i, cur.count + getTurnCnt(cur.direction, i) + 1));
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
			}
		}
	}

	public static int getTurnCnt(int d, int compare) {
		if (d == compare)
			return 0;
		if (d == 0)
			return compare == 1 ? 2 : 1;
		else if (d == 1)
			return compare == 0 ? 2 : 1;
		else if (d == 2)
			return compare == 3 ? 2 : 1;
		else
			return compare == 2 ? 2 : 1;
	}
}

class Point {
	int x, y, direction, count;

	public Point(int x, int y, int direction, int count) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}