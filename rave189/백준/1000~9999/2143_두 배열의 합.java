package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 두 배열 A와 B가 주어진다.
 * A의 부분 배열의 합과 B의 부분 배열의 합을 더한 값이 T가 되는 쌍의 개수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static ArrayList<Integer> b = new ArrayList<>();

	/**
	 * a배열의 누적합을 구한다.
	 * b의 모든 부분 배열을 구한다.
	 * b의 부분 배열을 hash에 넣은 후
	 * T - a의 부분 배열 = b의 부분 배열인 값을 hash에서 꺼내어 개수에 더해준다.
	 * 
	 * 이분 탐색으로는 시간 초과가 남
	 * 개수 세는 부분 때문인 거 같은데 어케 해야되지
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			a[i] = a[i - 1] + Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0, prevSize = 0, count = 0; i < m; i++) {
			int v = Integer.parseInt(st.nextToken());
			int size = b.size();
			for (int j = prevSize; j < size; j++)
				b.add(b.get(j) + v);
			b.add(v);
			prevSize += count++;
		}
		Map<Integer, Integer> hash = new HashMap<>();
		b.forEach(v -> hash.put(v, hash.getOrDefault(v, 0) + 1));
		long answer = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 0; j < i; j++)
				answer += hash.getOrDefault(t - (a[i] - a[j]), 0);

		System.out.println(answer);
	}
}