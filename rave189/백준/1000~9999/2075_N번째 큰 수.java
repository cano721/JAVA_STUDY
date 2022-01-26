package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (pq.size() < n)
					pq.add(value);
				else if (value > pq.peek()) {
					pq.poll();
					pq.add(value);
				}
			}
		}
		System.out.println(pq.poll());
	}
}