package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 트리 구조를 이루는 N개의 오두막이 있다.
 * 1번 오두막이 정상에 있다.
 * 두 오두막 i, j를 선택했을 때, i에서 j로 가는 거리 중 정상을 거치는 가장 짧은 길을 구하려고 한다.
 * 정상인 1번 오두막을 항상 거쳐가야 하고, 두 번 이상 지나간 길은 한 번만 센다.
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;
	static int n;
	static long TotalCombination, answer = 0;
	static int[] dp, distances, counts;

	/**
	 * 문제를 각 간선을 몇 번 사용하게 되는지로 바꾼다.
	 * u와 v를 잇는 간선 line(u, v)가 있을 때, 다음 4가지 경우가 있다.
	 * 1. line(u, v) 아래 서브 트리에서 i, j가 있는 경우
	 * 2. line(u, v) 아래 서브 트리에서 i만 있는 경우
	 * 3. line(u, v) 아래 서브 트리에서 j만 있는 경우
	 * 4. line(u, v) 아래 서브 트리에 i, j 모두 없는 경우
	 * 1, 2, 3은 모두 line(u, v)를 항상 지나야 한다.
	 * 따라서 전체 - 4번 경우를 빼면 line(u, v)를 사용하는 간선의 개수를 구할 수 있다.
	 * 따라서 모든 간선에 대해 위와 같은 방식을 수행한 결과를 더해주면 정답을 구할 수 있다.
	 * 
	 * 4번 경우는 line(u, v) 아래 서브트리를 제외한 노드들에서 i, j를 고르는 경우의 수로 구할 수 있다.
	 * 따라서 line(u, v) 아래 서브 트리의 노드 개수를 x라고 할 때, 4번 = (n-x)C2로 구할 수 있다.
	 * 이 때, 1번 노드에서 두 번 계산하게 됨을 유의한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new HashSet[n];
		dp = new int[n];
		distances = new int[n];
		counts = new int[n];
		TotalCombination = getCombination(n);
		for (int i = 0; i < n; i++)
			map[i] = new HashSet<>();
		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			map[first].add(second);
			map[second].add(first);
		}
		dfs(0, -1);
		// 1번에서 두 번 계산했으니 빼준다.
		System.out.println(answer - TotalCombination);
	}

	public static int dfs(int cur, int prev) {
		dp[cur] = 1;
		for (int next : map[cur]) {
			if (next == prev)
				continue;
			dp[cur] += dfs(next, cur);
		}
		answer += TotalCombination - getCombination(n - dp[cur]);
		return dp[cur];
	}

	public static long getCombination(long n) {
		return n * (n - 1) / 2;
	}
}