import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); 
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());			
			maxHeap.add(num); 
			minHeap.add(maxHeap.poll()); 
			
			if(maxHeap.size() < minHeap.size()) {
				maxHeap.add(minHeap.poll());
			}
			
			sb.append(maxHeap.peek()).append('\n');
		}
		
		System.out.println(sb);
		
	}
}
