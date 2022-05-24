package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N���� ���� ��û�� ���Դ�.
 * �� ������ D�� �ȿ� ���Ǹ� ���ָ� P���� ���� �� �ִ�.
 * �Ϸ翡 �ϳ��� ������ �� �� ���� ��, ���� �� �ִ� �ִ� �ݾ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static List<Integer>[] lectures;
	static final int MAX_DAY = 10000;

	/**
	 * �켱���� ť�� ����
	 * �׸���� �˽��޽�?
	 * 
	 * �ᱹ �з����� ���� �����س��� Ǯ��
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		lectures = new List[MAX_DAY + 1];
		for (int i = 0; i <= MAX_DAY; i++)
			lectures[i] = new ArrayList<>();
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			lectures[day].add(pay);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
		int answer = 0;
		for (int day = lectures.length - 1; day > 0; day--) {
			pq.addAll(lectures[day]);
			if (!pq.isEmpty())
				answer += pq.poll();
		}
		System.out.println(answer);
	}
}