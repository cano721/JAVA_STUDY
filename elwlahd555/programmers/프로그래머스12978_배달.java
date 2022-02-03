package elwlahd555.programmers;

public class 프로그래머스12978_배달 {
	public static void main(String[] args) {
		int N = 5;
		int[][] road = { { 1, 2, 1 }, { 2, 3, 3 }, { 5, 2, 2 }, { 1, 4, 2 }, { 5, 3, 1 }, { 5, 4, 2 } };
		int K = 3;
		System.out.println(solution(N, road, K));
	}

	private static int solution(int N, int[][] road, int K) {
		int answer = 0;
		int arr[][] = new int[N + 1][N + 1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i == j) {
					continue;
				}
				arr[i][j] = 500000;
			}
		}
		for (int i = 0; i < road.length; i++) {
			if (arr[road[i][0]][road[i][1]] > road[i][2]) {
				arr[road[i][0]][road[i][1]] = road[i][2];
				arr[road[i][1]][road[i][0]] = road[i][2];

			}

		}
		for (int path = 0; path < N + 1; path++) {
			for (int i = 0; i < N + 1; i++) {
				for (int j = 0; j < N + 1; j++) {
					if (arr[i][j] > arr[i][path] + arr[path][j]) {
						arr[i][j] = arr[i][path] + arr[path][j];
					}
				}
			}
		}
		for (int i = 0; i < N + 1; i++) {
			if (arr[1][i] <= K) {
				answer += 1;
			}
		}
		return answer;
	}
}
