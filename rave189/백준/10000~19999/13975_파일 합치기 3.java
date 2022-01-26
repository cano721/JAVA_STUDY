package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * �Ҽ� ���� �������� �ִ�.
 * �� ���ϵ��� ���ľ� �ϴµ� ���� ������ ��ġ�� ����� �ٸ���.
 * �� ���ϵ��� �ּ� ������� ��ġ���� �� ��, �ּҺ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * ���� ����� ���� ���Ϻ��� ��ġ�� �ּ� ������� ��ĥ �� �ִ�.
	 * ���� �켱���� ť�� ����� ���� ������ �� ������ ���� ��ģ��.
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