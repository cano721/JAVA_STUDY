package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 각 노드가 자식을 최대 K개 가질 수 있는 트리를 K진 트리라고 한다.
 * K진 트리의 두 노드가 주어졌을 때, 두 노드의 거리를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 노드의 개수가 10^15이기 때문에 트리를 만들려고 하면 안된다.
	 * 각 노드의 부모를 구하는 방법은 (n-1)/k를 올림하면 구할 수 있다.
	 * 약간 무식하게(?) 한쪽의 부모 노드를 모두 저장한 후 거기서 찾는 방식으로 품
	 * 
	 * 두 노드를 하나씩 올리면서 찾으면 더 빠른 시간에 구할 수 있다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		double k = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			long first = Long.parseLong(st.nextToken());
			long second = Long.parseLong(st.nextToken());
			if (k == 1) {
				answer.append(Math.abs(second - first)).append('\n');
				continue;
			}
			Map<Long, Long> hash = new HashMap<>();
			for (long tmp = first, idx = 1; tmp >= 1; tmp = (long) Math.ceil((tmp - 1) / k), idx++)
				hash.put(tmp, idx);
			for (long tmp = second, idx = 1; tmp >= 1; tmp = (long) Math.ceil((tmp - 1) / k), idx++) {
				if (hash.containsKey(tmp)) {
					answer.append(hash.get(tmp) + idx - 2).append('\n');
					break;
				}
			}
		}
		System.out.print(answer);
	}
}