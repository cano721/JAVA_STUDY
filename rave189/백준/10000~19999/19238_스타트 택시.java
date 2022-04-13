package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 택시가 M명의 승객을 모두 목적지까지 데려다 주고 난 후 남은 연료를 구하는 문제
 * 처음에는 F만큼의 연료를 가지고 있고 한 칸 이동시 1의 연료를 소모한다.
 * 운행을 하던 도중 연료를 모두 소모하면 -1을 출력한다.
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int f;
	static Queue<Point> taxi = new LinkedList<>();
	static Map<Point, Point> passengerHash = new HashMap<>();
	static PriorityQueue<Point> passengers = new PriorityQueue<>();

	/**
	 * 처음에는 연료당 움직임으로 while문을 돌렸는데 택시의 현재 위치와 승객의 현재 위치가 같을 수 있어 while을 승객당 움직임으로 바꾸었음
	 * 뭔가 코드가 살짝 마음에 안듬
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()) - 1;
		int y = Integer.parseInt(st.nextToken()) - 1;
		visited[x][y] = true;
		taxi.add(new Point(x, y));
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken()) - 1;
			int startY = Integer.parseInt(st.nextToken()) - 1;
			int endX = Integer.parseInt(st.nextToken()) - 1;
			int endY = Integer.parseInt(st.nextToken()) - 1;
			passengerHash.put(new Point(startX, startY), new Point(endX, endY));
		}
		while (m-- > 0) {
			findPassenger();
			int saveFuel = f;
			Point destination = moveDestination();
			f += (saveFuel - f) * 2;
			taxi.clear();
			taxi.add(destination);
		}
		System.out.println(f);
	}

	public static void findPassenger() {
		if (passengerHash.containsKey(taxi.peek())) {
			passengers.add(taxi.peek());
			return;
		}
		visited = new boolean[map.length][map[0].length];
		while (!taxi.isEmpty() && f > 0) {
			int size = taxi.size();
			while (size-- > 0) {
				Point cur = taxi.poll();
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (map[nextX][nextY] == 1 || visited[nextX][nextY])
							continue;
						Point next = new Point(nextX, nextY);
						if (passengerHash.containsKey(next))
							passengers.add(next);
						visited[nextX][nextY] = true;
						taxi.add(next);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
			f--;
			if (!passengers.isEmpty())
				return;
		}
		System.out.println(-1);
		System.exit(0);
	}

	public static Point moveDestination() {
		Point passenger = passengers.poll();
		Point destination = passengerHash.get(passenger);
		passengers.clear();
		passengerHash.remove(passenger);
		taxi.clear();
		taxi.add(passenger);
		visited = new boolean[map.length][map[0].length];
		visited[passenger.x][passenger.y] = true;
		while (!taxi.isEmpty() && f >= 0) {
			int size = taxi.size();
			while (size-- > 0) {
				Point cur = taxi.poll();
				if (cur.equals(destination))
					return cur;
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (map[nextX][nextY] == 1 || visited[nextX][nextY])
							continue;
						visited[nextX][nextY] = true;
						taxi.add(new Point(nextX, nextY));
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
			f--;
		}
		System.out.println(-1);
		System.exit(0);
		return null;
	}
}

class Point implements Comparable<Point> {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
		return x == other.x && y == other.y;
	}

	@Override
	public int compareTo(Point o) {
		if (x < o.x)
			return -1;
		else if (x == o.x)
			return y - o.y;
		return 1;
	}
}