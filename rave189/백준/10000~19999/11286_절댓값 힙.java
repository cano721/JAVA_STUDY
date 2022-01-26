package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) < Math.abs(o2))
					return -1;
				else if (Math.abs(o1) == Math.abs(o2))
					return o1 - o2;
				return 1;
			}
		});
		int count = Integer.parseInt(br.readLine());
		while (count-- > 0) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0)
				answer.append(pq.isEmpty() ? 0 : pq.poll()).append('\n');
			else
				pq.add(n);
		}
		System.out.print(answer);
	}
}