package prioirityQueue;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;


public class BJ1927_최대힙 {

	static int n;
	static PriorityQueue<Integer> heap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		heap = new PriorityQueue<>(Collections.reverseOrder()); //최대힙
		
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			getMinHeap(input);
			
		}
		
	}

	private static void getMinHeap(int input) {
		
		if(input == 0) {
			
			if(!heap.isEmpty()) {
				System.out.println(heap.poll());
			}else {
				System.out.println(0);
			}
		}
		else {
			heap.add(input);
		}
	}

}
