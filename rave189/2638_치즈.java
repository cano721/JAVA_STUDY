package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxM크기의 모눈종이에 치즈가 있다.
 * 이 치즈는 4변 중 2변 이상이 외부 공기와 접촉하면 1시간 후에 사라진다.
 * 치즈가 모두 사라지기까지 걸리는 시간을 구하는 문제
 * @author Rave
 *
 */
public class Main {
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<>();
	static List<Point> removeList = new LinkedList<>();
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * 평범한 bfs문제
	 * 
	 * 배열 전체 탐색하면 시간 오래걸릴거같아서 dfs로 했었는데
	 * 결국 bfs랑 다를게 없는 코드가 나오고, dfs 동작이 이상해져서 bfs로 바꿔서 맞춤
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					q.add(new Point(i, j));
			}
		}
		System.out.println(bfs());
	}

	public static int bfs() {
		int hour = 0;
		while (!q.isEmpty()) {
			hour++;
			isOutLine();
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				int outLineCnt = 0;
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					if (map[nextX][nextY] == -1)
						outLineCnt++;
				}
				if (outLineCnt < 2)
					q.add(cur);
				else
					removeList.add(cur);
			}
			remove();
		}
		return hour;
	}

	public static void isOutLine() {
		visited = new boolean[map.length][map[0].length];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			map[cur.x][cur.y] = -1;
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || map[nextX][nextY] == 1)
						continue;
					visited[nextX][nextY] = true;
					q.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}

	public static void remove() {
		for (Point cur : removeList)
			map[cur.x][cur.y] = 0;
		removeList.clear();
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}