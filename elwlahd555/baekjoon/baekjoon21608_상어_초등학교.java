package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class baekjoon21608_상어_초등학교 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int map[][] = new int[N + 1][N + 1];
		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };
		int answer = 0;
		HashMap<Integer, ArrayList<Integer>> check = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int people = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ArrayList<Integer> nearList = new ArrayList<>();
			nearList.add(a);
			nearList.add(b);
			nearList.add(c);
			nearList.add(d);
			check.put(people, nearList);
			int near = -1;
			int empty = -1;
			int xx = 0;
			int yy = 0;
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					int tempNear = 0;
					int tempEmpty = 0;
					if (map[j][k] != 0) {
						continue;
					}
					for (int z = 0; z < 4; z++) {
						int x = j + dx[z];
						int y = k + dy[z];
						if (x <= 0 || x > N || y <= 0 || y > N) {
							continue;
						}
						if (map[x][y] > 0 && (map[x][y] == a || map[x][y] == b || map[x][y] == c || map[x][y] == d)) {
							tempNear++;
						} else if (map[x][y] == 0) {
							tempEmpty++;
						}
					}
					if (tempNear > near) {
						near = tempNear;
						empty = tempEmpty;
						xx = j;
						yy = k;
					} else if (tempNear == near && tempEmpty > empty) {
						near = tempNear;
						empty = tempEmpty;
						xx = j;
						yy = k;
					}
				}
			}
			map[xx][yy] = people;

		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					if (x <= 0 || x > N || y <= 0 || y > N) {
						continue;
					}
					if (check.get(map[i][j]).contains(map[x][y])) {
						cnt++;
					}
				}
				switch (cnt) {
				case 0:
					answer += 0;
					break;
				case 1:
					answer += 1;
					break;
				case 2:
					answer += 10;
					break;
				case 3:
					answer += 100;
					break;
				case 4:
					answer += 1000;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}
