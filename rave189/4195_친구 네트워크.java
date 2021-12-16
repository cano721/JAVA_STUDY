package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ģ�� ���谡 �־�����.
 * ģ�� ���谡 �־��� ������ ģ�� ��Ʈ��ũ�� ����� �ִ��� ���ϴ� ����
 * ģ�� ��Ʈ��ũ�� ģ�� ���踸���� �̵��� �� �ִ� ���̸� ���Ѵ�.
 * @author Rave
 *
 */
public class Main {

	static Map<String, Integer> hash = new HashMap<>();
	static int[] unionFind, relationship;

	/**
	 * ���Ͽ� ���ε带 ����Ͽ� ģ�� ���踦 ���Ѵ�.
	 * �Է°��� �̸����� ������ ������ hash�� ����Ͽ� �ε����� �ο����ش�.
	 * ģ�� ���踦 ���� ���ؼ� ����Ϸ��� �ð��� �����ɸ��� ������ relationship �迭�� �ϳ� �����
	 * ģ�� ���谡 �ξ��� ������ ģ�� ������ ���� ���ʿ� �����ش�.
	 * 
	 * ģ�� ������ ���� ���� ���Ϸ��� �ϴٺ��� �ð��ʰ��� Ǯ�� ����.
	 * ������ �ڵ�� ó�� �����ߴ� �Ͱ� �Ȱ���.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			unionFind = new int[2 * n];
			relationship = new int[2 * n];
			for (int i = 0; i < unionFind.length; i++) {
				unionFind[i] = i;
				relationship[i] = 1;
			}
			int count = 0;
			hash.clear();
			while (n-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String first = st.nextToken();
				String second = st.nextToken();
				if (!hash.containsKey(first))
					hash.put(first, count++);
				if (!hash.containsKey(second))
					hash.put(second, count++);
				answer.append(union(hash.get(first), hash.get(second))).append('\n');
			}
		}
		System.out.println(answer);
	}

	public static int union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			unionFind[b] = unionFind[a];
			relationship[a] += relationship[b];
		}
		return relationship[a];
	}

	public static int find(int n) {
		if (n == unionFind[n])
			return n;
		return unionFind[n] = find(unionFind[n]);
	}
}