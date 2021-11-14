package prioirityQueue;


import java.io.*;
import java.util.*;

public class BJ15903_카드합체놀이 {
	static int n,m;
	static int min = Integer.MAX_VALUE;
	static PriorityQueue<Long> heap; //각 값이 최대인 1,000,000 될 수 있기 때문에 int형으로 담을 수 없음!
	static long[] cards;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		cards = new long[n];
		heap = new PriorityQueue<>(); //최소힙
		
		for (int i = 0; i < n; i++) {
			cards[i]=Integer.parseInt(st.nextToken());
			heap.add(cards[i]);
		}
		
		for (int i = 0; i < m; i++) {
			long a = heap.poll();
			long b = heap.poll();
			
			long sum = a+b;
			
			heap.add(sum);
			heap.add(sum);
		}
		
		long ans = 0;
		for (int i = 0; i < n; i++) {
			ans += heap.poll();
		}
		
		System.out.println(ans);
		
	}

}
