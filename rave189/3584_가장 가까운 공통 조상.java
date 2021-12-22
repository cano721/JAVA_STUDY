package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 트리가 주어지고, 두 노드가 주어진다.
 * 두 노드에서 가장 가까운 공통 조상을 구하는 문제(LCA)
 * @author Rave
 *
 */
public class Main {

	static ArrayList<Integer>[] parents;
	static HashSet<Integer> hash = new HashSet<>();

	/**
	 * 자신의 부모를 ArrayList에 모두 담는다.
	 * 이후 주어진 두 노드 중 하나의 노드의 모든 조상을 dfs로 탐색하여 hash에 담는다.
	 * 나머지 하나의 노드를 탐색하며 hash에 조상이 있으면 그 값을 반환한다.
	 * 
	 * LCA로 안풀고 DFS로 풀었음.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			parents = new ArrayList[n + 1];
			for (int i = 0; i <= n; i++)
				parents[i] = new ArrayList<>();
			hash.clear();
			for (int i = 1; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				parents[child].add(parent);
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			int firstNode = Integer.parseInt(st.nextToken());
			int secondNode = Integer.parseInt(st.nextToken());
			answer.append(solution(firstNode, secondNode)).append('\n');
		}
		System.out.print(answer);
	}

	public static int solution(int firstNode, int secondNode) {
		findAnscestor(firstNode);
		return getLowestCommonAnscestor(secondNode);
	}

	public static void findAnscestor(int cur) {
		hash.add(cur);
		for (int parent : parents[cur])
			findAnscestor(parent);
	}

	public static int getLowestCommonAnscestor(int cur) {
		if (hash.contains(cur))
			return cur;
		int result = -1;
		for (int parent : parents[cur]) {
			result = getLowestCommonAnscestor(parent);
			if (result != -1)
				return result;
		}
		return -1;
	}
}