package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Class[] classes = new Class[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			classes[i] = new Class(start, end);
		}
		Arrays.sort(classes, (v1, v2) -> v1.startTime - v2.startTime);
		PriorityQueue<Class> q = new PriorityQueue<>((v1, v2) -> v1.endTime - v2.endTime);
		int answer = 0;
		for (Class curClass : classes) {
			while (!q.isEmpty() && curClass.startTime >= q.peek().endTime)
				q.poll();
			q.add(curClass);
			answer = Math.max(answer, q.size());
		}
		System.out.println(answer);
	}
}

class Class {
	int startTime, endTime;

	public Class(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
}