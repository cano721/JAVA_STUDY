package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N명의 사람이 있다.
 * 이 중에서 회장을 선출하려고 한다.
 * 회장을 선출하는 기준은 각 회원의 점수가 가장 적은 사람이 회장이 될 수 있다.
 * 점수는 각 회원이 다른 회원들과 가까운 정도에 따라 점수를 받게 된다.
 * 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다.
 * 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나 친구의 친구임을 말한다.
 * 또한 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친구의 친구의 친구임을 말한다.
 * 이 때, 회장의 점수와 회장이 될 수 있는 모든 사람을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][] relationship;
	static int INF = 200000000;

	/**
	 * 너비 우선 탐색으로 무난하게 풀리는 문제지만 플로이드 와샬로는 잘 안풀리는 문제였음
	 * 우선 플로이드 와샬을 사용하여 각 회원들간의 거리를 구한다.
	 * 이후 각 회원과 다른 회원들간의 최대 거리를 구하고, 이 때 가장 작은 값을 구한다.
	 * 각 회원과 다른 회원들간의 거리가 가장 작은 값인 경우를 모두 찾는다.
	 * 
	 * 플로이드 와샬을 사용하기 전 관계를 INF로 정의할 때, 자기 자신은 0으로 초기화 해야 한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		relationship = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(relationship[i], INF);
			relationship[i][i] = 0;
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			if (first == -2 && second == -2)
				break;
			relationship[first][second] = 1;
			relationship[second][first] = 1;
		}
		for (int t = 0; t < n; t++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					relationship[i][j] = Math.min(relationship[i][j], relationship[i][t] + relationship[t][j]);
		int[] score = new int[n];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				score[i] = Math.max(score[i], relationship[i][j]);
			min = Math.min(min, score[i]);
		}
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++)
			if (score[i] == min)
				result.add(i);
		for (int v : result)
			answer.append(v + 1).append(' ');
		System.out.println(min + " " + result.size());
		System.out.println(answer);
	}
}