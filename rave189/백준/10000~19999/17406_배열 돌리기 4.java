package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 문제 r, c, s가 주어진다.
 * r, c를 기준으로 (r-s, c-s) ~ (r+s, c+s) 범위를 돌린다.
 * 돌리는 것은 시계방향으로 가장 바깥 라인부터 돌린다.
 * 단, 회전이 여러 개가 주어지는데 회전의 순서가 달라지면 전체 값도 달라진다.
 * 회전을 모두 한 번씩 사용하여 배열의 최솟값을 구하는 문제
 * 배열의 값은 각 행의 숫자의 합 중 가장 작은 값이다.
 * 
 * @author Rave
 *
 */
public class Main {

	static int[][] rotateArr;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	/**
	 * 주어진대로 구현
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		rotateArr = new int[k][];
		visited = new boolean[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			rotateArr[i] = new int[] { r, c, s };
		}
		bruteforce(map, k);
		System.out.println(answer);
	}

	public static void bruteforce(int[][] map, int depth) {
		if (depth == 0) {
			answer = Math.min(answer, getMinOfArrayRow(map));
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			int[][] copyMap = copy(map);
			rotate(copyMap, rotateArr[i][0], rotateArr[i][1], rotateArr[i][2]);
			bruteforce(copyMap, depth - 1);
			visited[i] = false;
		}
	}

	public static int[][] copy(int[][] map) {
		int[][] copy = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++)
			System.arraycopy(map[i], 0, copy[i], 0, map[0].length);
		return copy;
	}

	public static void rotate(int[][] map, int r, int c, int s) {
		int minX = r - s, maxX = r + s, minY = c - s, maxY = c + s;
		for (int i = 0; i < s; i++)
			rotateOutside(map, minX + i, minY + i, maxX - i, maxY - i);
	}

	public static void rotateOutside(int[][] map, int startX, int startY, int endX, int endY) {
		int tmp = map[startX][startY];
		for (int i = startX; i < endX; i++)
			map[i][startY] = map[i + 1][startY];
		for (int i = startY; i < endY; i++)
			map[endX][i] = map[endX][i + 1];
		for (int i = endX; i > startX; i--)
			map[i][endY] = map[i - 1][endY];
		for (int i = endY; i > startY; i--)
			map[startX][i] = map[startX][i - 1];
		map[startX][startY + 1] = tmp;
	}

	public static int getMinOfArrayRow(int[][] map) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < map.length; i++) {
			int sum = 0;
			for (int j = 0; j < map[0].length; j++)
				sum += map[i][j];
			min = Math.min(min, sum);
		}
		return min;
	}
}