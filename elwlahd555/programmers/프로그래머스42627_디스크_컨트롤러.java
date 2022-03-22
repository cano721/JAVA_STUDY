package elwlahd555.programmers;

import java.util.Collections;
import java.util.LinkedList;

public class 프로그래머스42627_디스크_컨트롤러 {
	public static void main(String[] args) {
		int jobs[][] = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
		System.out.println(solution(jobs));
	}

	static class Job implements Comparable<Job> {
		int start, during, end;

		public Job(int start, int during, int end) {
			super();
			this.start = start;
			this.during = during;
			this.end = end;
		}

		@Override
		public int compareTo(Job o) {
			return this.during - o.during;
		}

	}

	public static int solution(int[][] jobs) {
		int answer = 0;

		LinkedList<Job> que = new LinkedList<Job>();
		LinkedList<Job> result = new LinkedList<Job>();
		for (int i = 0; i < jobs.length; i++) {
			que.add(new Job(jobs[i][0], jobs[i][1], 0));
		}
		int time = 0;
		Collections.sort(que);
		while (!que.isEmpty()) {
			Job j = null;
			for (int i = 0; i < que.size(); i++) {
				if (que.get(i).start <= time) {
					j = que.get(i);
					que.remove(i);
					break;
				}
			}
			if (j == null) {
				time++;
			} else {
				time += j.during;
				result.add(new Job(j.start, j.during, time));

			}

		}
		for (Job job : result) {
			answer += job.end - job.start;
		}
		answer /= result.size();
		return answer;
	}
}
