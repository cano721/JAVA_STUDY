package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[] isLinked = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			isLinked[i] = i;
		}
		
		
		PriorityQueue<Link> link = new PriorityQueue<Link>(new Comparator<Link>() {
			@Override
			public int compare(Link o1, Link o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			link.add(new Link(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		
		int answer = 0;
		while(!link.isEmpty()) {
			Link now = link.poll();
			if(getParent(isLinked, now.a) != getParent(isLinked, now.b)) {
				union(isLinked, now.a, now.b);
				answer += now.weight;
			} 
		}
		
		System.out.println(answer);
		
		
		
		
	}
	static class Link{
		int a;
		int b;
		int weight;
		
		public Link(int a, int b, int weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
	}
	static int getParent(int[] arr, int a) {
		if(arr[a] == a) return a;
		
		return arr[a] = getParent(arr, arr[a]);
	}
	
	static void union(int[] arr, int a, int b) {
		a = getParent(arr, a);
		b = getParent(arr, b);
		
		if(a < b) arr[b] = a;
		else arr[a] = b;
	}
}	