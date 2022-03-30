package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * NxN ���ڰ� �ִ�.
 * ������ �� ĭ���� �ٱ��ϰ� �ְ� ���� ����ִ�.
 * �� ó�� (N, 1), (N, 2), (N-1, 1), (N-1, 2)�� ������ �ִ�.
 * ������ 8 �������� ������ �� �ִ�.
 * ���� ������ �����϶� (1, 1)���� ���� �ö󰡸� (N, 1) �������� ���� (1, N)�� ���� �ݴ��ʿ��� ���� �� �ִ�.
 * �� ��, ������ ���� �ൿ�� M�� �� �� ��ü ���� ���� ���� ���ϴ� ����
 * 1. ��� ������ di �������� siĭ �̵��Ѵ�.
 * 2. �� �������� �� ���� ������ �ִ� ĭ�� �ٱ��Ͽ� ����� ���� ���� 1 �����Ѵ�.
 * 3. ������ ��� �������.
 * 4. 2���� ���� ������ ĭ (r, c)�� ��������� ������ �����Ѵ�. ��������� ������ ����ϸ�, �밢�� �������� �Ÿ��� 1�� ĭ�� ���� �ִ� �ٱ����� ����ŭ (r, c)�� �ִ� �ٱ����� ���� ���� �����Ѵ�.
 * 4.1 �̶��� �̵��� �ٸ��� ��踦 �Ѿ�� ĭ�� �밢�� �������� �Ÿ��� 1�� ĭ�� �ƴϴ�.
 * 4.2 ���� ���, (N, 2)���� ������ �밢�� ĭ�� (N-1, 1), (N-1, 3)�̰�, (N, N)���� ������ �밢�� ĭ�� (N-1, N-1)���̴�.
 * 5. �ٱ��Ͽ� ����� ���� ���� 2 �̻��� ��� ĭ�� ������ �����, ���� ���� 2 �پ���. �̶� ������ ����� ĭ�� 3���� ������ ����� ĭ�� �ƴϾ�� �Ѵ�.
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
	 * ��Ű�´�� �����Ѵ�.
	 * (0, 0)���� (N-1, N-1)�� ��ұ� ������ ó�� ������ -1�ؼ� �����Ѵ�.
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