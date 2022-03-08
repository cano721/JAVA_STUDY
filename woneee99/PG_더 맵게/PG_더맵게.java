import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class PG_더맵게 {

	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 7;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();

//		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < scoville.length; i++)
			q.add(scoville[i]);
		int count = 0;
		while (q.peek() < K) {
			int min1 = q.poll();
			int min2 = q.poll();
			q.add(min1 + min2 * 2);
			count++;

			if (q.peek() >= K)
				break;
			if (q.size() == 1 && q.peek() < K) {
				count = -1;
				break;
			}
//			Collections.sort(arr);
			/*
			 * if(arr.get(0)<K) { int x = arr.get(0)+(arr.get(1)*2); arr.remove(0);
			 * arr.remove(0); arr.add(x); count++; }
			 */
			/*
			 * else { break; }
			 */
		}
		System.out.println(count);
	}

}
