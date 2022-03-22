package programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class d220322_디스크컨트롤러 {

	public static void main(String[] args) {
		int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
		int answer = solution(jobs);
		System.out.println(answer);
	}

	private static int solution(int[][] jobs) {
		int answer = 0;
		int count = 0;
		int idx = 0;
		int lastTime = 0;
		int n = jobs.length;
		//요청 순서로 정렬
		Arrays.sort(jobs, (o1, o2) -> {
			if(o1[0] == o2[0]) {
				return Integer.compare(o1[1], o2[1]);
			} else {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		
		//응답시간 순서로 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[1], o2[1]);
		});
		
		while(count < n) {
			//처리시간 동안 요청된 건 queue에 담기
			while(idx < n && jobs[idx][0] <= lastTime) {
				pq.offer(jobs[idx++]);
			}
			
			if(pq.isEmpty()) {
				lastTime = jobs[idx][0];
			}else {
				int[] job = pq.poll();
				lastTime += job[1];
				answer += lastTime - job[0];
				count++;
			}
		}
		
		return (int)Math.floor(answer / n);
	}

}
