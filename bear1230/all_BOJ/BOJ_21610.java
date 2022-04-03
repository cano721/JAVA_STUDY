import java.util.*;
import java.io.*;


public class Main {

	public static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int n;
	public static int[][] map;
	public static boolean[][] isCloud;
	public static int[][] moveList;
	public static ArrayList<Cloud> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		isCloud = new boolean[n][n];
		moveList = new int[m][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			moveList[i][0] = d;
			moveList[i][1] = s;

		}
		list = new ArrayList<Cloud>();
		list.add(new Cloud(n - 1, 0));
		list.add(new Cloud(n - 1, 1));
		list.add(new Cloud(n - 2, 0));
		list.add(new Cloud(n - 2, 1));

		for (int i = 0; i < m; i++) {
			move(moveList[i][0], moveList[i][1]);
			copyWater();
			createCloud();

		}
		System.out.println(getSum());

	}

	public static void move(int d, int s) {
		for (Cloud cloud : list) {
			int nextX = (cloud.x + n + (dy[d] * s) % n) % n;
			int nextY = (cloud.y + n + (dx[d] * s) % n) % n;
			map[nextX][nextY]++;
			isCloud[nextX][nextY] = true;
			cloud.x = nextX;
			cloud.y = nextY;
		}
	}

	public static void copyWater() {
		for (Cloud cloud : list) {
			int addWater = 0;
			for (int i = 1; i <= 4; i++) {
				int nextX = cloud.x + dy[2 * i];
				int nextY = cloud.y + dx[2 * i];

				if ((0 <= nextX && nextX < n) && (0 <= nextY && nextY < n)) {
					if (map[nextX][nextY] > 0) {
						addWater++;
					}
				} else
					continue;
			}
			map[cloud.x][cloud.y] += addWater;
		}
		list.clear();
	}

	public static void createCloud() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (isCloud[i][j]) {
					isCloud[i][j] = false;
					continue;
				} else if (!isCloud[i][j] && map[i][j] >= 2) {
					map[i][j] -= 2;
					list.add(new Cloud(i, j));
				} else
					continue;
			}
		}
	}

	public static int getSum() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

	public static class Cloud {
		int x;
		int y;

		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
