package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n2075_N번째큰수 {
	
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < n; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		int i = 0;
		int answer = Integer.MIN_VALUE;
		while(i < n) {
			answer = pq.poll();
			i++;
		}
		
		System.out.println(answer);

	}

}
