package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * N*N 크기의 교실이 있다.
 * 각 학생들은 1번부터 N*N번까지 번호가 있다.
 * 이 학생들에게 자리를 배정해주려고 한다.
 * 학생의 자리는 입력 순서로 배정해 주면 된다.
 * 이 때, 다음과 같은 조건으로 배정한다.
 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
 * 위와 같은 조건으로 모든 학생을 배정한 후 각 학생들의 만족도를 조사하려고 한다.
 * 주변에 좋아하는 학생이 0명이면 0, 1명이면 1, 2명이면 10, 3명이면 100, 4명이면 1000의 만족도를 가진다.
 * 모든 학생의 만족도의 합을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static final int MAX_FEEL_COUNT = 4;
	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * 주어진 대로 구현한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		int[][] likeStudents = new int[n * n + 1][];
		for (int i = n * n; i > 0; i--) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int studentNo = Integer.parseInt(st.nextToken());
			likeStudents[studentNo] = new int[MAX_FEEL_COUNT];
			for (int j = 0; j < MAX_FEEL_COUNT; j++)
				likeStudents[studentNo][j] = Integer.parseInt(st.nextToken());
			studentAssign(studentNo, likeStudents[studentNo]);
		}
		System.out.println(calcSatisfying(likeStudents));
	}

	public static void studentAssign(int studentNo, int[] likeStudent) {
		ArrayList<Seat> arr = new ArrayList<>();
		HashSet<Integer> hash = new HashSet<>();
		for (int student : likeStudent)
			hash.add(student);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] != 0)
					continue;
				int likeStudentCnt = getLikeStudentCount(i, j, hash);
				int blinkCnt = getBlinkSeatCount(i, j);
				Seat seat = new Seat(i, j, likeStudentCnt, blinkCnt);
				arr.add(seat);
			}
		}
		arr.sort(Comparator.naturalOrder());
		Seat assign = arr.get(0);
		map[assign.x][assign.y] = studentNo;
	}

	public static int getLikeStudentCount(int x, int y, HashSet<Integer> likeStudents) {
		int feelCnt = 0;
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			try {
				if (likeStudents.contains(map[nextX][nextY]))
					feelCnt++;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return feelCnt;
	}

	public static int getBlinkSeatCount(int x, int y) {
		int blinkCnt = 0;
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			try {
				if (map[nextX][nextY] == 0)
					blinkCnt++;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return blinkCnt;
	}

	public static int calcSatisfying(int[][] likeStudents) {
		int sum = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int studentNo = map[i][j];
				HashSet<Integer> likeStudentHash = new HashSet<>();
				for (int t = 0; t < likeStudents[studentNo].length; t++)
					likeStudentHash.add(likeStudents[studentNo][t]);
				int likeStudentCnt = getLikeStudentCount(i, j, likeStudentHash);
				sum += Math.pow(10, likeStudentCnt - 1);
			}
		}
		return sum;
	}
}

class Seat implements Comparable<Seat> {
	int x, y, likeStudentCnt, blinkCnt;

	public Seat(int x, int y, int likeStudentCnt, int blinkCnt) {
		this.x = x;
		this.y = y;
		this.likeStudentCnt = likeStudentCnt;
		this.blinkCnt = blinkCnt;
	}

	@Override
	public int compareTo(Seat o) {
		if (likeStudentCnt > o.likeStudentCnt)
			return -1;
		else if (likeStudentCnt == o.likeStudentCnt)
			if (blinkCnt > o.blinkCnt)
				return -1;
			else if (blinkCnt == o.blinkCnt)
				if (x < o.x)
					return -1;
				else if (x == o.x)
					return y - o.y;
		return 1;
	}

	@Override
	public String toString() {
		return "Seat [x=" + x + ", y=" + y + ", likeStudentCnt=" + likeStudentCnt + ", blinkCnt=" + blinkCnt + "]";
	}
}