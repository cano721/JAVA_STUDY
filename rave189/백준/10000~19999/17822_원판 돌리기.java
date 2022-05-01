package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * ���� N���� �־�����.
 * �� ���ǿ��� M���� ������ �����ִ�.
 * i��° ������ i-1�� i+1��° ������ �����ϴ�. (1�� M�� ����)
 * i��° ������ j��° ������ (i-1, j), (i+1, j)�� �����ϴ�. (1�� N�� �������� �ʴ�)
 * ���� x�� �־����� x�� ����� ������ ��� �ð� �Ǵ� �ݽð�� k��ŭ ȸ���Ѵ�.
 * d = 0�̸� �ð� ����, d = 1�̸� �ݽð� �����̴�.
 * ȸ���� ������ ������ ���� �� ���� ���� �ִٸ� �����.
 * ���� ������ ���� ���� �������� �ʴ´ٸ� �����ϴ� ���� ��ü ����� ���Ѵ�.
 * ��պ��� ���� ���� -1, ���� ���� +1�� ���ش�.
 * @author Rave
 *
 */
public class Main {

	static int[][] circles;
	static int n, m;

	/**
	 * ���� ��ü�� �ݹ� �� �� ������ ���� üũ ������ �� �����ɸ�.
	 * ���δ� ���� ������ �����ε�, ���δ� ������ �ƴ� �� ������ �� �ɸ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		circles = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				circles[i][j] = Integer.parseInt(st.nextToken());
		}
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken()) == 0 ? 1 : -1;
			int rotateCnt = Integer.parseInt(st.nextToken());
			for (int i = 1; i * idx <= n; i++)
				rotate(i * idx - 1, direction, rotateCnt);
			search();
		}
		System.out.println(getSumOfCircles());
	}

	public static void rotate(int idx, int direction, int cnt) {
		int[] newCircle = new int[m];
		for (int i = 0; i < m; i++) {
			int next = getValidValue(i + cnt * direction);
			newCircle[next] = circles[idx][i];
		}
		circles[idx] = newCircle;
	}

	public static int getValidValue(int num) {
		if (num < 0)
			return num + m;
		else if (num >= m)
			return num % m;
		else
			return num;
	}

	public static void search() {
		HashSet<Point> hash = new HashSet<>();
		for (int i = 0; i < n; i++)
			for (int j = 1; j <= m; j++)
				if (circles[i][j % m] != 0 && circles[i][j - 1] == circles[i][j % m]) {
					hash.add(new Point(i, j - 1));
					hash.add(new Point(i, j % m));
				}
		for (int j = 0; j < m; j++)
			for (int i = 1; i < n; i++)
				if (circles[i][j] != 0 && circles[i - 1][j] == circles[i][j]) {
					hash.add(new Point(i - 1, j));
					hash.add(new Point(i, j));
				}

		if (hash.isEmpty())
			calc();
		else
			remove(hash);
	}

	public static void calc() {
		double sum = getSumOfCircles();
		int remainNumCnt = getRemainNumCnt();
		double avg = sum / remainNumCnt;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (circles[i][j] == 0)
					continue;
				if (circles[i][j] > avg)
					circles[i][j]--;
				else if (circles[i][j] < avg)
					circles[i][j]++;
			}
		}
	}

	public static void remove(HashSet<Point> hash) {
		for (Point cur : hash)
			circles[cur.x][cur.y] = 0;
	}

	public static int getSumOfCircles() {
		int sum = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				sum += circles[i][j];
		return sum;
	}

	public static int getRemainNumCnt() {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (circles[i][j] > 0)
					count++;
		return count;
	}
}

class Point {
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
}