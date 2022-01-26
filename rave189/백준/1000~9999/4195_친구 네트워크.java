package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 친구 관계가 주어진다.
 * 친구 관계가 주어질 때마다 친구 네트워크에 몇명이 있는지 구하는 문제
 * 친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.
 * @author Rave
 *
 */
public class Main {

	static Map<String, Integer> hash = new HashMap<>();
	static int[] unionFind, relationship;

	/**
	 * 유니온 파인드를 사용하여 친구 관계를 구한다.
	 * 입력값이 이름으로 들어오기 때문에 hash를 사용하여 인덱스를 부여해준다.
	 * 친구 관계를 직접 구해서 출력하려면 시간이 오래걸리기 때문에 relationship 배열을 하나 만들고
	 * 친구 관계가 맺어질 때마다 친구 관계의 수를 한쪽에 더해준다.
	 * 
	 * 친구 관계의 수를 직접 구하려고 하다보니 시간초과로 풀지 못함.
	 * 나머지 코드는 처음 생각했던 것과 똑같다.
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