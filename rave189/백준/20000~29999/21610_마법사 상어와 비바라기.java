package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * NxN 격자가 있다.
 * 격자의 각 칸에는 바구니가 있고 물이 들어있다.
 * 맨 처음 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 구름이 있다.
 * 구름은 8 방향으로 움직일 수 있다.
 * 또한 구름이 움직일때 (1, 1)에서 위로 올라가면 (N, 1) 왼쪽으로 가면 (1, N)과 같이 반대쪽에서 나올 수 있다.
 * 이 때, 다음과 같은 행동을 M번 한 뒤 전체 물의 양의 합을 구하는 문제
 * 1. 모든 구름이 di 방향으로 si칸 이동한다.
 * 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 * 3. 구름이 모두 사라진다.
 * 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
 * 4.1 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 * 4.2 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 * 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] isRemovedCloud;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static ArrayList<Point> clouds = new ArrayList<>();

	/**
	 * 시키는대로 구현한다.
	 * (0, 0)에서 (N-1, N-1)로 잡았기 때문에 처음 구름을 -1해서 생성한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		init(n);
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			moveCloud(d, s % n);
			rain();
			removeCloud();
			copyMagic();
			createCloud();
		}
		System.out.println(getSum());
	}

	public static void init(int n) {
		clouds.add(new Point(n - 1, 0));
		clouds.add(new Point(n - 1, 1));
		clouds.add(new Point(n - 2, 0));
		clouds.add(new Point(n - 2, 1));
	}

	public static int getSum() {
		int sum = 0;
		for (int[] row : map)
			for (int cell : row)
				sum += cell;
		return sum;
	}

	public static void moveCloud(int d, int s) {
		for (Point cloud : clouds) {
			int nextX = validValue(cloud.x + dx[d] * s);
			int nextY = validValue(cloud.y + dy[d] * s);
			cloud.move(nextX, nextY);
		}
	}

	public static int validValue(int n) {
		if (n < 0)
			return n += map.length;
		else if (n >= map.length)
			return n % map.length;
		return n;
	}

	public static void rain() {
		for (Point cloud : clouds) {
			map[cloud.x][cloud.y]++;
		}
	}

	public static void removeCloud() {
		isRemovedCloud = new boolean[map.length][map[0].length];
		for (Point cloud : clouds)
			isRemovedCloud[cloud.x][cloud.y] = true;
	}

	public static void copyMagic() {
		for (Point increaseBasket : clouds) {
			int existWaterBasketCnt = 0;
			for (int i = 1; i < dx.length; i += 2) {
				int nextX = increaseBasket.x + dx[i];
				int nextY = increaseBasket.y + dy[i];
				try {
					if (map[nextX][nextY] > 0)
						existWaterBasketCnt++;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			map[increaseBasket.x][increaseBasket.y] += existWaterBasketCnt;
		}
		clouds.clear();
	}

	public static void createCloud() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (isRemovedCloud[i][j] || map[i][j] < 2)
					continue;
				clouds.add(new Point(i, j));
				map[i][j] -= 2;
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

	public void move(int nextX, int nextY) {
		x = nextX;
		y = nextY;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}