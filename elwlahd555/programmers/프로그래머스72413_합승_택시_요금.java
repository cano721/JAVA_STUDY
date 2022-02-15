package elwlahd555.programmers;

public class 프로그래머스72413_합승_택시_요금 {
	public static void main(String[] args) {
		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
		System.out.println(solution(n, s, a, b, fares));
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] node = new int[n + 1][n + 1];
		for (int i = 0; i < n + 1; i++) {
			for (int j = 0; j < n + 1; j++) {
				node[i][j] = 20000001;
			}
			node[i][i] = 0;
		}
		for (int i = 0; i < fares.length; i++) {
			node[fares[i][0]][fares[i][1]] = fares[i][2];
			node[fares[i][1]][fares[i][0]] = fares[i][2];
		}
		for (int k = 1; k < n + 1; k++) {
			for (int i = 1; i < n + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
					if (node[i][j] > node[i][k] + node[k][j]) {
						node[i][j] = node[i][k] + node[k][j];
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < n + 1; i++) {
			min = Math.min(min, node[s][i] + node[i][a] + node[i][b]);
		}
		return min;
	}
}
