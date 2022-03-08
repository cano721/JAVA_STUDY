package programmers;

import java.util.PriorityQueue;

public class d220308_더맵게 {

	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		int answer = solution(scoville, K);
		System.out.println(answer);	//2
	}

	private static int solution(int[] scoville, int k) {
		int answer = 0;
		PriorityQueue<Integer> scovil = new PriorityQueue<Integer>();
		for (int i = 0; i < scoville.length; i++) {
			scovil.add(scoville[i]);
		}
		while(true) {
			if(scovil.peek() >= k) break;
			else if(scovil.size() == 1) {
				answer = -1;
				break;
			}
			int a = scovil.poll();
			a += scovil.poll() * 2;
			scovil.add(a);
			answer++;
		}
		return answer;
	}

}
