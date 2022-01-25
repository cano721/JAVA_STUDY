package 유형별문제풀이.prioirityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2075_N번째큰수 {

	static int n;
	static PriorityQueue<Integer> heap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		heap = new PriorityQueue<>(Collections.reverseOrder()); //최대힙
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				heap.add(temp);
			}

		}
		
		for (int i = 1; i <= heap.size(); i++) {
			if(i==n) {
				System.out.println(heap.peek());
				break;
			}
			heap.poll();
		}

	}

}
