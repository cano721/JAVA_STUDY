package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		PriorityQueue<Integer> left = new PriorityQueue<>((v1, v2) -> v2 - v1);
		PriorityQueue<Integer> right = new PriorityQueue<>();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			int num = Integer.parseInt(br.readLine());
			if (left.isEmpty() || num < left.peek())
				left.add(num);
			else if (right.isEmpty() || num > right.peek())
				right.add(num);
			else
				left.add(num);
			if (right.size() > left.size())
				left.add(right.poll());
			else if (left.size() > right.size() + 1)
				right.add(left.poll());
			answer.append(left.peek()).append('\n');
		}
		System.out.print(answer);
	}
}