package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n19638_센티와마법의뿅망치 {
	static int n, h, t;
	static int num = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(Integer.parseInt(st.nextToken()));
		}
		int num = 0;
		for (int i = 0; i < t; i++) {
			if(pq.peek() < h || pq.peek() == 1) break;
			num++;
			pq.add(pq.poll()/2);
		}
		if(pq.peek() < h) {
			System.out.println("YES");
			System.out.println(num);
		}else {
			System.out.println("NO");
			System.out.println(pq.poll());
		}
		
	}

}
