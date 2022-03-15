package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon2613_숫자구슬 {
	private static int arr[];
	private static int ansMap[];
	private static int map[];
	private static int N, M, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N];
		arr = new int[M - 1];
		answer = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < map.length; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		DFS(0, 1);
		System.out.println(answer);
		for (int i = 0; i < ansMap.length; i++) {
			System.out.print(ansMap[i] + " ");
		}
	}

	private static void DFS(int cnt, int start) {
		if (cnt == M - 1) {
			int left = 0;
			int right = arr[0];
			int temp[] = new int[M];
			for (int i = left; i < right; i++) {
				temp[0] += map[i];
			}

			for (int i = 0; i < arr.length; i++) {
				left = arr[i];
				if (i == M - 2) {
					right = N;
				} else {
					right = arr[i + 1];
				}
				for (int j = left; j < right; j++) {
					temp[i + 1] += map[j];
				}
			}
			int tempAnswer = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] > tempAnswer) {
					tempAnswer = temp[i];
				}
			}
			if (answer > tempAnswer) {
				answer = tempAnswer;
				ansMap = new int[M];
				for (int i = 0; i < ansMap.length; i++) {
					if (i == 0) {
						ansMap[i] = arr[i];
					} else if (i == M - 1) {
						ansMap[i] = N - arr[i - 1];
					} else {
						ansMap[i] = arr[i] - arr[i - 1];
					}
				}
			}
			return;
		}
		for (int i = start; i < N; i++) {
			arr[cnt] = i;
			DFS(cnt + 1, i + 1);
		}
	}
}
