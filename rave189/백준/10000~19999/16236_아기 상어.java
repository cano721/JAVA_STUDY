package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int sharkSize = 2, eatCount = 0, move;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		Point cur = null;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					cur = new Point(i, j);
			}
		}
		int answer = 0;
		while (true) {
			ArrayList<Point> foods = bfs(cur);
			if (foods.size() == 0) {
				break;
			}
			Point eat = getEatFood(foods, cur);
			map[cur.x][cur.y] = 0;
			map[eat.x][eat.y] = 9;
			answer += move;
			cur = eat;
			eatCount++;
			if (eatCount == sharkSize) {
				sharkSize++;
				eatCount = 0;
			}
		}
		System.out.println(answer);
	}

	public static ArrayList<Point> bfs(Point start) {
		ArrayList<Point> foods = new ArrayList<>();
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[map.length][map[0].length];
		q.add(start);
		visited[start.x][start.y] = true;
		move = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (map[nextX][nextY] > sharkSize || visited[nextX][nextY])
							continue;
						Point next = new Point(nextX, nextY);
						visited[nextX][nextY] = true;
						if (0 < map[nextX][nextY] && map[nextX][nextY] < sharkSize)
							foods.add(next);
						q.add(next);
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
			move++;
			if (foods.size() > 0)
				break;
		}
		return foods;
	}

	public static Point getEatFood(ArrayList<Point> foods, Point cur) {
		int distance = Integer.MAX_VALUE;
		ArrayList<Point> closeFood = new ArrayList<>();
		for (Point food : foods) {
			int d = cur.getDistance(food);
			if (d < distance) {
				closeFood.clear();
				closeFood.add(food);
				distance = d;
			} else if (d == distance)
				closeFood.add(food);
		}
		Point eat = closeFood.get(0);
		for (Point food : closeFood) {
			if (eat.compareTo(food) > 0) {
				eat = food;
			}
		}
		return eat;
	}
}

class Point implements Comparable<Point> {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (x < o.x)
			return -1;
		else if (x == o.x)
			return y - o.y;
		return 1;
	}

	public int getDistance(Point o) {
		return Math.abs(x - o.x) + Math.abs(y - o.y);
	}
}