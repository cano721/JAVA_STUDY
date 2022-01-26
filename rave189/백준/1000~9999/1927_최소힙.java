package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int count = Integer.parseInt(br.readLine());
		while (count-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0)
				answer.append(pq.isEmpty() ? 0 : pq.poll()).append('\n');
			else
				pq.add(num);
		}
		System.out.println(answer);
	}
}