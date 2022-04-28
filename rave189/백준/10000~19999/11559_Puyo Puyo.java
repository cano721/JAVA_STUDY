package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static final int ROW = 12, COL = 6;
	static char[][] map = new char[ROW][COL];
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < ROW; i++) {
			String line = br.readLine();
			for (int j = 0; j < COL; j++)
				map[i][j] = line.charAt(j);
		}
		int answer = 0;
		while (true) {
			Queue<Point> bombs = getBombsPuyo();
			if (bombs.isEmpty())
				break;
			answer++;
			bomb(bombs);
			dropPuyoByGravity();
		}
		System.out.println(answer);
	}

	public static Queue<Point> getBombsPuyo() {
		Queue<Point> q = new LinkedList<>();
		visited = new boolean[ROW][COL];
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if (map[i][j] != '.' && !visited[i][j]) {
					ArrayList<Point> puyos = getSameTypePuyo(i, j);
					if (puyos.size() >= 4)
						q.addAll(puyos);
				}
			}
		}
		return q;
	}

	public static ArrayList<Point> getSameTypePuyo(int x, int y) {
		ArrayList<Point> puyos = new ArrayList<>();
		puyos.add(new Point(x, y));
		visited[x][y] = true;
		char type = map[x][y];
		for (int idx = 0; idx < puyos.size(); idx++) {
			Point cur = puyos.get(idx);
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (map[nextX][nextY] != type || visited[nextX][nextY])
						continue;
					visited[nextX][nextY] = true;
					puyos.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return puyos;
	}

	public static void bomb(Queue<Point> bombs) {
		while (!bombs.isEmpty()) {
			Point cur = bombs.poll();
			map[cur.x][cur.y] = '.';
		}
	}

	public static void dropPuyoByGravity() {
		for (int i = 0; i < COL; i++) {
			for (int j = ROW - 1; j >= 0; j--) {
				if (map[j][i] == '.') {
					for (int t = j - 1; t >= 0; t--) {
						if (map[t][i] != '.') {
							char tmp = map[j][i];
							map[j][i] = map[t][i];
							map[t][i] = tmp;
							break;
						}
					}
				}
			}
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