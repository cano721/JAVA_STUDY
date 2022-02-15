package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int INF = 200000000;

	/**
	 * 시작 지점 s에서 a지점과 b지점으로 두 사람이 택시를 타고 가려고 한다.
	 * 두 사람은 택시 요금을 절약하기 위해 합승하여 가는 것이 요금이 싸다면 합승하려고 한다.
	 * 두 사람이 a지점과 b지점으로 가는 최소 비용을 구하는 문제
	 * 합승을 굳이 하지 않아도 된다.
	 * 
	 * 플로이드 와샬로 모든 구간에 대해 최단 거리를 구하고, s -> i -> a, b의 값을 더한 최솟값을 찾는다.
	 * @param n 노드의 개수
	 * @param s 시작 지점
	 * @param a a의 도착 지점
	 * @param b b의 도착 지점
	 * @param fares 각 구간 별 이용 요금
	 * @return s에서 a와 b로 가는 최솟값
	 */
	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = Integer.MAX_VALUE;
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				if (i != j)
					map[i][j] = INF;
		for (int[] fare : fares) {
			map[fare[0]][fare[1]] = fare[2];
			map[fare[1]][fare[0]] = fare[2];
		}
		for (int k = 1; k <= n; k++)
			for (int i = 1; i <= n; i++)
				for (int j = 1; j <= n; j++)
					if (map[i][k] + map[k][j] < map[i][j])
						map[i][j] = map[i][k] + map[k][j];
		for (int i = 1; i <= n; i++)
			answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
		return answer;
	}
}