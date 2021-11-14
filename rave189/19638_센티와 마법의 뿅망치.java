package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 센티가 거인 마을에 갔다.
 * 거인들의 키가 센티보다 모두 작게 하고 싶어 마법의 뿅망치를 사용한다.
 * 뿅망치는 거인의 키를 나누기 2로 하고 소수점은 버림한다.
 * 뿅망치를 T번 사용할 수 있을 때 모든 거인의 키를 센티보다 작게할 수 있는지 구하는 문제
 * 센티보다 작게 할 수 있다면 첫째 줄에 YES를, 둘째 줄에 뿅망치를 사용한 최소 횟수를 출력한다.
 * 센티보다 작게하지 못한다면 첫째 줄에 NO를, 둘째 줄에 가장 키가 큰 거인의 키를 출력한다.
 * @author Rave
 *
 */
public class Main {

	/**
	 * 거인의 키를 우선순위 큐에 넣어두고 뿅망치를 키가 큰 거인부터 사용한다.
	 * 횟수를 채우거나 가장 키가 큰 거인이 센티보다 작다면 출력한다.
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