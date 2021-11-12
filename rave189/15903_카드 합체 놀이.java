package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N장의 자연수가 적힌 카드가 주어진다.
 * 이 카드 중 두 장의 카드를 골라 더한다.
 * 더한 값을 두 장의 카드에 덮어 쓴다.
 * 위와 같은 과정을 M번 하려고 할 때, 만들 수 있는 최솟값을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 입력값을 다 더하면 int 범위를 초과한다.
	 * 따라서 long을 사용해야 통과할 수 있다.
	 * 문제 이해하는데 시간이 오래걸림
	 * 그냥 작은 수부터 다 더해주면 되는데...
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		while (n-- > 0)
			pq.add(Long.parseLong(st.nextToken()));
		while (m-- > 0) {
			long sum = pq.poll() + pq.poll();
			pq.add(sum);
			pq.add(sum);
		}
		long sum = 0;
		while (!pq.isEmpty())
			sum += pq.poll();
		System.out.println(sum);
	}
}