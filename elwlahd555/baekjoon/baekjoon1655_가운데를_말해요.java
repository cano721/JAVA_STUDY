package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class baekjoon1655_가운데를_말해요 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> min = new PriorityQueue<Integer>((o1, o2) -> o1 - o2);
		PriorityQueue<Integer> max = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (min.size() == max.size())
				max.offer(num);
			else
				min.offer(num);

			if (!min.isEmpty() && !max.isEmpty())
				if (min.peek() < max.peek()) {
					int tmp = min.poll();
					min.offer(max.poll());
					max.offer(tmp);
				}

			sb.append(max.peek() + "\n");
		}
		System.out.println(sb);
	}
}
