package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N���� �ڿ����� ���� ī�尡 �־�����.
 * �� ī�� �� �� ���� ī�带 ��� ���Ѵ�.
 * ���� ���� �� ���� ī�忡 ���� ����.
 * ���� ���� ������ M�� �Ϸ��� �� ��, ���� �� �ִ� �ּڰ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * �Է°��� �� ���ϸ� int ������ �ʰ��Ѵ�.
	 * ���� long�� ����ؾ� ����� �� �ִ�.
	 * ���� �����ϴµ� �ð��� �����ɸ�
	 * �׳� ���� ������ �� �����ָ� �Ǵµ�...
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