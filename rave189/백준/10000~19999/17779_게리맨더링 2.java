package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전체 구역에 대한 인구수가 주어진다.
 * 전체 구역을 5개의 구역으로 나누려고 한다.
 * 구역은 다음과 같이 나눈다.
 * 1. 기준점 (x, y)와 경계의 길이 d1, d2를 정한다. (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
 * 2. 다음 칸은 경계선이다.
 * 	1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
 * 	2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
 * 	3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
 * 	4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
 * 3. 경계선과 경계선의 안에 포함되어있는 곳은 5번 선거구이다.
 * 4. 5번 선거구에 포함되지 않은 구역 (r, c)의 선거구 번호는 다음 기준을 따른다.
 * 	1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
 * 	2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
 * 	3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
 * 	4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
 * 나누어진 5개의 구역의 최대 인구수 - 최소 인구수의 최솟값을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][] map, area;
	static int[] peopleOfArea;
	static final int AREA_SIZE = 5;
	static int n, answer = Integer.MAX_VALUE;

	/**
	 * 빡구현
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++)
				for (int d1 = 1; d1 < map.length; d1++)
					for (int d2 = 1; d2 < map.length; d2++)
						divide(x, y, d1, d2);
		System.out.println(answer);
	}

	public static void divide(int x, int y, int d1, int d2) {
		peopleOfArea = new int[AREA_SIZE];
		area = new int[map.length][map[0].length];
		try {
			fifth(x, y, d1, d2);
			first(x, y, d1, d2);
			second(x, y, d1, d2);
			third(x, y, d1, d2);
			fourth(x, y, d1, d2);
			calc();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public static void fifth(int x, int y, int d1, int d2) {
		for (int i = x, j = y; i <= x + d1; i++, j--)
			area[i][j] = 5;
		for (int i = x, j = y; i < x + d2; i++, j++)
			area[i][j] = 5;
		for (int i = x + d1, j = y - d1; i <= x + d1 + d2; i++, j++)
			area[i][j] = 5;
		for (int i = x + d2, j = y + d2; i <= x + d2 + d1; i++, j--)
			area[i][j] = 5;
		for (int i = x, j = y; i <= x + d1; i++, j--)
			area[i][j] = 5;
		for (int i = x + 1, j = y - 1; i <= x + d1; i++, j--)
			for (int t = j + 1; t < map[0].length; t++)
				if (area[i][t] == 5)
					break;
				else
					area[i][t] = 5;
		for (int i = x + d1, j = y - d1; i < x + d1 + d2; i++, j++)
			for (int t = j + 1; t < map[0].length; t++)
				if (area[i][t] == 5)
					break;
				else
					area[i][t] = 5;
	}

	public static void first(int x, int y, int d1, int d2) {
		for (int i = 0; i < x + d1; i++)
			for (int j = 0; j <= y; j++)
				if (area[i][j] == 0)
					area[i][j] = 1;
	}

	public static void second(int x, int y, int d1, int d2) {
		for (int i = 0; i <= x + d2; i++)
			for (int j = y + 1; j < map[0].length; j++)
				if (area[i][j] == 0)
					area[i][j] = 2;
	}

	public static void third(int x, int y, int d1, int d2) {
		for (int i = x + d1; i < map.length; i++)
			for (int j = 0; j < y - d1 + d2; j++)
				if (area[i][j] == 0)
					area[i][j] = 3;
	}

	public static void fourth(int x, int y, int d1, int d2) {
		for (int i = x + d2 + 1; i < map.length; i++)
			for (int j = y - d1 + d2; j < map[0].length; j++)
				if (area[i][j] == 0)
					area[i][j] = 4;
	}

	public static void calc() {
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				peopleOfArea[area[i][j] - 1] += map[i][j];

		int max = 0, min = Integer.MAX_VALUE;
		for (int people : peopleOfArea) {
			max = Math.max(max, people);
			min = Math.min(min, people);
		}
		answer = Math.min(answer, max - min);
	}
}