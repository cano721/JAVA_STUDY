import java.util.*;

class Solution {
	public int[] solution(int[] progresses, int[] speeds) {

		Vector<Integer> v = new Vector<Integer>();

		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < progresses.length; i++) {
			int day = 0;
			while (progresses[i] < 100) {
				progresses[i] += speeds[i];
				day++;
			}
			q.add(day);
		}
		int cur = q.peek();
		int result = 0;
		while (!q.isEmpty()) {
			if (q.peek() <= cur) {
				result++;
				q.poll();
			} else {
				cur = q.peek();
				v.add(result);
				result = 0;
			}
		}
		v.add(result);
		int[] answer = new int[v.size()];

		for (int i = 0; i < answer.length; i++) {
			answer[i] = v.elementAt(i);
		}
		return answer;

	}
}
