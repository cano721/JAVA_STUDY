import java.util.*;
public class PG_다리를지나는트럭 {

	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();

		Queue<Integer> wait = new LinkedList<>();
		
		int bridge_length = 2;
		int weight = 9;
		int[] truck_weights = {7, 4, 5, 6};
		
		for(int k : truck_weights) q.add(k);
		
		int count = 0;
		
		while(true) {
			int x = q.poll();
			wait.add(x);
			
			while(true) {
				if(q.peek() + x <= weight) {
					wait.add(q.peek());
					count -= 1;
					x += q.poll();
				}
				else {
					int k = wait.poll();
					count += bridge_length;
					x -= k;
				}
				if(wait.isEmpty() && q.isEmpty()) break;
				
			}
		}

		
	}
}
