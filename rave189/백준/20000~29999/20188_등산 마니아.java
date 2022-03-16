package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Ʈ�� ������ �̷�� N���� ���θ��� �ִ�.
 * 1�� ���θ��� ���� �ִ�.
 * �� ���θ� i, j�� �������� ��, i���� j�� ���� �Ÿ� �� ������ ��ġ�� ���� ª�� ���� ���Ϸ��� �Ѵ�.
 * ������ 1�� ���θ��� �׻� ���İ��� �ϰ�, �� �� �̻� ������ ���� �� ���� ����.
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;
	static int n;
	static long TotalCombination, answer = 0;
	static int[] dp, distances, counts;

	/**
	 * ������ �� ������ �� �� ����ϰ� �Ǵ����� �ٲ۴�.
	 * u�� v�� �մ� ���� line(u, v)�� ���� ��, ���� 4���� ��찡 �ִ�.
	 * 1. line(u, v) �Ʒ� ���� Ʈ������ i, j�� �ִ� ���
	 * 2. line(u, v) �Ʒ� ���� Ʈ������ i�� �ִ� ���
	 * 3. line(u, v) �Ʒ� ���� Ʈ������ j�� �ִ� ���
	 * 4. line(u, v) �Ʒ� ���� Ʈ���� i, j ��� ���� ���
	 * 1, 2, 3�� ��� line(u, v)�� �׻� ������ �Ѵ�.
	 * ���� ��ü - 4�� ��츦 ���� line(u, v)�� ����ϴ� ������ ������ ���� �� �ִ�.
	 * ���� ��� ������ ���� ���� ���� ����� ������ ����� �����ָ� ������ ���� �� �ִ�.
	 * 
	 * 4�� ���� line(u, v) �Ʒ� ����Ʈ���� ������ ���鿡�� i, j�� ���� ����� ���� ���� �� �ִ�.
	 * ���� line(u, v) �Ʒ� ���� Ʈ���� ��� ������ x��� �� ��, 4�� = (n-x)C2�� ���� �� �ִ�.
	 * �� ��, 1�� ��忡�� �� �� ����ϰ� ���� �����Ѵ�.
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
		// 1������ �� �� ��������� ���ش�.
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