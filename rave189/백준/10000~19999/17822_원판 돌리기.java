package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 원판 N개가 주어진다.
 * 각 원판에는 M개의 정수가 쓰여있다.
 * i번째 정수는 i-1과 i+1번째 정수와 인접하다. (1과 M도 인접)
 * i번째 원판의 j번째 정수는 (i-1, j), (i+1, j)와 인접하다. (1과 N은 인접하지 않다)
 * 정수 x가 주어지면 x의 배수인 원판을 모두 시계 또는 반시계로 k만큼 회전한다.
 * d = 0이면 시계 방향, d = 1이면 반시계 방향이다.
 * 회전이 끝나면 인접한 수들 중 같은 수가 있다면 지운다.
 * 만약 인접한 같은 수가 존재하지 않는다면 존재하는 수의 전체 평균을 구한다.
 * 평균보다 높은 수는 -1, 낮은 수는 +1을 해준다.
 * @author Rave
 *
 */
public class Main {

	static int[][] circles;
	static int n, m;

	/**
	 * 구현 자체는 금방 한 것 같은데 조건 체크 때문에 좀 오래걸림.
	 * 가로는 끝과 시작이 인접인데, 세로는 인접이 아닌 것 때문에 좀 걸림.
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