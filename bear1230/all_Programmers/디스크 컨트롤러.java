import java.util.*;

class Task implements Comparable<Task> {
	int start;
	int work;

	public Task(int start, int work) {
		this.start = start;
		this.work = work;
	}

	@Override
	public int compareTo(Task t) {
		return this.work - t.work;
	}
}

class Solution {

	public int solution(int[][] jobs) {
		int answer = 0;
		Arrays.sort(jobs, new Comparator<>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int idx = 0;
		int cur = 0;

		PriorityQueue<Task> pq = new PriorityQueue<>();
		while (true) {
			while (idx < jobs.length && jobs[idx][0] <= cur) {
				pq.add(new Task(jobs[idx][0], jobs[idx][1]));
				idx++;
			}
			if (pq.isEmpty()) {
				if (idx == jobs.length) {
					break;
				} else {
					cur++;
				}
			} else {
				Task t = pq.poll();
				cur += t.work;
				answer += cur - t.start;
			}

		}

		return answer / jobs.length;
	}
}
