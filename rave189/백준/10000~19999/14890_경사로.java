package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map, rotateMap;
	static int l, answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		rotateMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				rotateMap[j][i] = map[i][j];
		for (int i = 0; i < map.length; i++) {
			if (isPossible(map[i]))
				answer++;
			if (isPossible(rotateMap[i]))
				answer++;
		}
		System.out.println(answer);
	}

	public static boolean isPossible(int[] road) {
		int type = road[0], cnt = 1;
		for (int i = 1; i < road.length; i++) {
			if (Math.abs(type - road[i]) > 1)
				return false;
			if (road[i] > type) {
				if (cnt >= l) {
					cnt = 1;
					type = road[i];
				} else
					return false;
			} else if (road[i] == type)
				cnt++;
			else {
				if (cnt < 0)
					return false;
				type = road[i];
				cnt = 1 - l;
			}
		}
		return cnt >= 0;
	}
}