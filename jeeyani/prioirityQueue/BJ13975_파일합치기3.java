package prioirityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13975_파일합치기3 {

	static int t,k;
	static long[] price;
	static PriorityQueue<Long> heap;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < t; i++) {
			
			k = Integer.parseInt(br.readLine());
			price = new long[k];
			heap = new PriorityQueue<Long>();
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < k; j++) {
				price[j] = Integer.parseInt(st.nextToken());
				heap.add(price[j]);
			}
			long ans = 0;
			for (int j = 0; j < k-1; j++) {
			
				long a = heap.poll();
				long b = heap.poll();
				
				long sum = a+b;
				
				heap.add(sum);	
				ans += sum;
			}
			System.out.println(ans);
		
		}
	}

}
