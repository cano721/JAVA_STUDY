import java.util.*;

class PG_다리를지나는트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<int[]> q = new LinkedList<>();
		int cur = 0, cnt = 0, time = 0;
		int index = 0;

		while (index < truck_weights.length) {
			time++;

			if (!q.isEmpty()) {
				int[] pos = q.peek();
				if (time - pos[0] == bridge_length) {
					q.poll();
					cur -= pos[1];
					cnt -= 1;
				}
			}
			if (truck_weights[index] + cur <= weight && cnt + 1 <= bridge_length) {
				cur += truck_weights[index];
				cnt++;
				q.offer(new int[] { time, truck_weights[index++] });
			}
		}
		Object[] answer = q.toArray();
		int[] temp = (int[]) answer[answer.length - 1];
		return temp[0] + bridge_length;
	}
}