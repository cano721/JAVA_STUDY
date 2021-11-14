package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 소설 파일 여러개가 있다.
 * 이 파일들을 합쳐야 하는데 각각 파일을 합치는 비용이 다르다.
 * 이 파일들을 최소 비용으로 합치려고 할 때, 최소비용을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 가장 비용이 적은 파일부터 합치면 최소 비용으로 합칠 수 있다.
	 * 따라서 우선순위 큐에 비용이 작은 값부터 두 파일을 꺼내 합친다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			PriorityQueue<Long> pq = new PriorityQueue<>();
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (n-- > 0)
				pq.add(Long.parseLong(st.nextToken()));
			long answer = 0;
			while (pq.size() > 1) {
				long sum = pq.poll() + pq.poll();
				answer += sum;
				pq.add(sum);
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
}