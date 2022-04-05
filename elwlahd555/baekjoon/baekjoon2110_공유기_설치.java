package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon2110_공유기_설치 {
	private static int N, C, result;
	private static int map[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		result = 0;
		map = new int[N];
		for (int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(map);
		int low = 1;
		int high = map[N - 1] - map[0];
		while (low <= high) {
			int mid = (low + high) / 2;
			int start = map[0];
			int cnt = 1;
			for (int i = 0; i < N; i++) {
				if (map[i] - start >= mid) {
					cnt++;
					start = map[i];
				}
			}
			if (cnt >= C) {
				result = mid;
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		System.out.println(result);
	}
}
