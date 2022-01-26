package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Ʈ���� �־�����, �� ��尡 �־�����.
 * �� ��忡�� ���� ����� ���� ������ ���ϴ� ����(LCA)
 * @author Rave
 *
 */
public class Main {

	static ArrayList<Integer>[] parents;
	static HashSet<Integer> hash = new HashSet<>();

	/**
	 * �ڽ��� �θ� ArrayList�� ��� ��´�.
	 * ���� �־��� �� ��� �� �ϳ��� ����� ��� ������ dfs�� Ž���Ͽ� hash�� ��´�.
	 * ������ �ϳ��� ��带 Ž���ϸ� hash�� ������ ������ �� ���� ��ȯ�Ѵ�.
	 * 
	 * LCA�� ��Ǯ�� DFS�� Ǯ����.
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