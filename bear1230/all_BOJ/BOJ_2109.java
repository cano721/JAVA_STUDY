import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Course> q = new PriorityQueue<>();
		int day = 0, ans = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			day = Math.max(day, d);
			q.add(new Course(p, d));
		}
		
		boolean[] visited = new boolean[day + 1];
		while (!q.isEmpty()) {
			Course c = q.poll();
			for (int i = c.d; i >= 1; i--) {
				if (!visited[i]) {
					visited[i] = true;
					ans += c.p;
					break;
				}
			}
		}
		System.out.println(ans);
	}

	static class Course implements Comparable<Course> {
		int p, d;
		public Course(int p, int d) {
			this.p = p;
			this.d = d;

		}

		public int compareTo(Course o) {
			if (this.p == o.p)
				return this.d - o.d;

			else
				return o.p - this.p;

		}

	}

}
