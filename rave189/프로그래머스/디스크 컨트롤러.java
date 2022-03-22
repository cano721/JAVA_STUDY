package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };
		System.out.println(solution.solution(jobs));
	}
}

class Solution {
	public int solution(int[][] jobs) {
		PriorityQueue<Job> pq = new PriorityQueue<>();
		Job[] jobList = new Job[jobs.length];
		for (int i = 0; i < jobs.length; i++)
			jobList[i] = new Job(jobs[i][0], jobs[i][1]);
		Arrays.sort(jobList, (v1, v2) -> v1.requestTime - v2.requestTime);
		int curTime = 0, answer = 0;
		for (int i = 0; !(i == jobList.length && pq.isEmpty());) {
			if (pq.isEmpty())
				curTime = Math.max(curTime, jobList[i].requestTime);
			while (i < jobList.length && (pq.isEmpty() || jobList[i].requestTime <= curTime))
				pq.add(jobList[i++]);
			Job cur = pq.poll();
			curTime = Math.max(curTime, cur.requestTime);
			curTime += cur.runTime;
			answer += curTime - cur.requestTime;
		}
		return answer / jobs.length;
	}
}

class Job implements Comparable<Job> {
	int requestTime, runTime;

	public Job(int requestTime, int runTime) {
		this.requestTime = requestTime;
		this.runTime = runTime;
	}

	@Override
	public int compareTo(Job o) {
		if (runTime < o.runTime)
			return -1;
		else if (runTime == o.runTime)
			return requestTime - o.requestTime;
		return 1;
	}
}