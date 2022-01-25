package 유형별문제풀이.prioirityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BJ11286_절댓값힙 {
	
	static int n;
	static PriorityQueue<Integer> heap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		//Comparator 오버라이딩
		heap = new PriorityQueue<>((o1,o2) ->{
			if(Math.abs(o1) == Math.abs(o2)) return o1 > o2? 1: -1;
			return Math.abs(o1)-Math.abs(o2);
		});
		
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
