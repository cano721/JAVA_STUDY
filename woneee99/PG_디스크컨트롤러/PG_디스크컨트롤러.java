import java.util.*;
import java.io.*;

class PG_디스크컨트롤러 {
	public int solution(int[][] jobs) {

		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

		int count = 0, idx = 0, answer = 0, end = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		
		while (count < jobs.length) {

			while (idx < jobs.length && jobs[idx][0] <= end) {
				pq.add(jobs[idx++]);
			}

			if (pq.isEmpty()) {
				end = jobs[idx][0];
			} else {
				int[] x = pq.poll();
				answer += x[1] + end - x[0];
				end += x[1];
				count++;
			}
		}

		return (int) Math.floor(answer / jobs.length);
	}
}