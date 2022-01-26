package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * ��Ƽ�� ���� ������ ����.
 * ���ε��� Ű�� ��Ƽ���� ��� �۰� �ϰ� �;� ������ �и�ġ�� ����Ѵ�.
 * �и�ġ�� ������ Ű�� ������ 2�� �ϰ� �Ҽ����� �����Ѵ�.
 * �и�ġ�� T�� ����� �� ���� �� ��� ������ Ű�� ��Ƽ���� �۰��� �� �ִ��� ���ϴ� ����
 * ��Ƽ���� �۰� �� �� �ִٸ� ù° �ٿ� YES��, ��° �ٿ� �и�ġ�� ����� �ּ� Ƚ���� ����Ѵ�.
 * ��Ƽ���� �۰����� ���Ѵٸ� ù° �ٿ� NO��, ��° �ٿ� ���� Ű�� ū ������ Ű�� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	/**
	 * ������ Ű�� �켱���� ť�� �־�ΰ� �и�ġ�� Ű�� ū ���κ��� ����Ѵ�.
	 * Ƚ���� ä��ų� ���� Ű�� ū ������ ��Ƽ���� �۴ٸ� ����Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		while (n-- > 0)
			pq.add(Integer.parseInt(br.readLine()));
		int count = 0;
		while (count < t && pq.peek() >= h) {
			if (pq.peek() > 1)
				pq.add(pq.poll() / 2);
			count++;
		}
		if (pq.peek() >= h) {
			System.out.println("NO");
			System.out.println(pq.poll());
		} else {
			System.out.println("YES");
			System.out.println(count);
		}
	}
}