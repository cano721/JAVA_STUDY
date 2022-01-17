package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 어떤 성에는 벽과 통로가 있다.
 * 벽으로 둘러싸인 곳은 방이다.
 * 이 성의 벽에 대한 정보가 하나의 정수로 주어진다.
 * 서쪽에 벽이 있으면 1, 북쪽은 2, 동쪽은 4, 남쪽은 8의 수를 더한 값으로 주어진다.
 * 이때, 이 성에 있는 방의 개수, 가장 넓은 방의 넓이, 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static final int[] DX = { 0, -1, 0, 1 };
	static final int[] DY = { -1, 0, 1, 0 };
	static int[][] map;
	static boolean[][] visited;

	/**
	 * bfs와 bruteforce를 사용하여 문제를 품
	 * 대신 시간이 오래 걸림
	 * 
	 * 단지 번호 붙이기 처럼 각 방을 index화하고, index가 다른 부분을 더했을 때의 최종 값을 구하는 방식이 훨씬 빠름
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (!visited[i][j]) {
					count++;
					max = Math.max(max, getAreaSize(new Point(i, j)));
				}
		System.out.println(count);
		System.out.println(max);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				for (int t = 0; t < DX.length; t++) {
					if ((map[i][j] & (1 << t)) == (1 << t)) {
						visited = new boolean[n][m];
						int wall = (int) Math.pow(2, t);
						map[i][j] -= wall;
						max = Math.max(max, getAreaSize(new Point(i, j)));
						map[i][j] += wall;
					}
				}
		System.out.println(max);
	}

	public static int getAreaSize(Point start) {
		Queue<Point> q = new LinkedList<>();
		int count = 0;
		q.add(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			count++;
			for (int i = 0; i < DX.length; i++) {
				if ((map[cur.x][cur.y] & (1 << i)) == (1 << i))
					continue;
				int nextX = cur.x + DX[i];
				int nextY = cur.y + DY[i];
				try {
					if (visited[nextX][nextY])
						continue;
					q.add(new Point(nextX, nextY));
					visited[nextX][nextY] = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					return -1;
				}
			}
		}
		return count;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}