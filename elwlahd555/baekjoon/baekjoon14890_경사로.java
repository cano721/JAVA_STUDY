package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14890_경사로 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for (int i = 0; i < N; i++) {
			int start = 0;
			int cnt = 1;
			boolean result = true;
			for (int j = 0; j < map.length; j++) {
				if (start == 0) {
					start = map[i][j];
					cnt = 1;
				} else if (start == map[i][j]) {
					cnt++;
				} else if (start == map[i][j] - 1) {
					if (cnt >= L) {
						start = map[i][j];
						cnt = 1;
					} else {
						result = false;
						break;
					}
				} else if (start == map[i][j] + 1) {
					if (j + L > N) {
						result = false;
						break;
					}
					for (int k = j; k < L + j; k++) {
						if (map[i][k] != map[i][j]) {
							result = false;
							break;
						}
					}
					if (result) {
						start = map[i][j];
						j += L-1;
						cnt = 0;
					} else {
						break;
					}
				} else {
					result = false;
					break;
				}
			}
			if (result) {
				answer++;
			}
			start = 0;
			cnt = 1;
			result = true;
			for (int j = 0; j < map.length; j++) {
				if (start == 0) {
					start = map[j][i];
					cnt = 1;
				} else if (start == map[j][i]) {
					cnt++;
				} else if (start == map[j][i] - 1) {
					if (cnt >= L) {
						start = map[j][i];
						cnt = 1;
					} else {
						result = false;
						break;
					}
				} else if (start == map[j][i] + 1) {
					if (j + L > N) {
						result = false;
						break;
					}
					for (int k = j; k < L + j; k++) {
						if (map[k][i] != map[j][i]) {
							result = false;
							break;
						}
					}
					if (result) {
						start = map[j][i];
						j += L-1;
						cnt = 0;
					} else {
						break;
					}
				} else {
					result = false;
					break;
				}
			}
			if (result) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}
