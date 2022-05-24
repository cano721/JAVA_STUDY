package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N개의 강연 요청이 들어왔다.
 * 각 강연은 D일 안에 강의를 해주면 P원을 받을 수 있다.
 * 하루에 하나의 강연을 할 수 있을 때, 받을 수 있는 최대 금액을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static List<Integer>[] lectures;
	static final int MAX_DAY = 10000;

	/**
	 * 우선순위 큐는 맞춤
	 * 그리디는 알쏭달쏭?
	 * 
	 * 결국 분류보고 역순 생각해내서 풀음
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