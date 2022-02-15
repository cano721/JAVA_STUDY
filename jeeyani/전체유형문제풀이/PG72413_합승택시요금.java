package 전체유형문제풀이;

import java.util.*;

/*

[플로이드-와샬]
taxi[i][j]로 가는 비용보다 더 작은 값으로 taxi[i][j]로 갈 수 있는 경로를 발견한다면 그 값으로 갱신


====================
처음엔 [MST알고리즘인 prim알고리즘]으로 풀려고 했다,..
하지만 이 알고리즘은 도착 노드가 정해져 있지 않고! 최단 경로만 반환하기에 적절하지 않았음!!


** 다익스트라 알고리즘도 가능!

*/

public class PG72413_합승택시요금 {

	public static void main(String[] args) {

		int n = 6;
		int s = 4;
		int a = 6;
		int b = 2;
		int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
				{ 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };

		int result = solution(n, s, a, b, fares);

		System.out.println(result);

	}

	private static int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;

		int[][] taxi = new int[n + 1][n + 1];

		//초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					//taxi[i][j] =  100001; //범위부족

					taxi[i][j] = 20000001; //200 * 100000 + 1
				}
			}
		}

		for (int[] fare : fares) {

			int x = fare[0];
			int y = fare[1];

			taxi[x][y] = taxi[y][x] = fare[2];

		}

		//taxi[i][j]로 가는 비용보다 더 작은 값으로 taxi[i][j]로 갈 수 있는 경로를 발견한다면 그 값으로 갱신
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (taxi[i][j] > taxi[i][k] + taxi[k][j]) {
						taxi[i][j] = taxi[i][k] + taxi[k][j];
					}
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, taxi[s][i] + taxi[i][a] + taxi[i][b]);
		}

		return answer;
	}

}