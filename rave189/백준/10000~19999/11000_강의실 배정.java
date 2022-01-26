package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	/**
	 * ´Ù½Ã Ç®¾îºÁ¾ß´îµí
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Lecture[] lectures = new Lecture[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			lectures[i] = new Lecture(start, end);
		}
		Arrays.sort(lectures);
		PriorityQueue<Lecture> pq = new PriorityQueue<>((v1, v2) -> v1.end - v2.end);
		int answer = 0;
		for (Lecture next : lectures) {
			while (!pq.isEmpty() && pq.peek().end <= next.start)
				pq.remove();
            pq.add(next);
			answer = Math.max(answer, pq.size());
		}
		System.out.println(answer);
	}
}

class Lecture implements Comparable<Lecture> {
	int start, end;

	public Lecture(int start, int end) {
		this.start = start;
		this.end = end;
	}
    
	@Override
	public int compareTo(Lecture o) {
		return start - o.start;
	}
}