package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String[] NM = br.readLine().split(" ");
		
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		if(N == 2) {
			System.out.println(0);
			return;
		}
		
		ArrayList<Link> arr = new ArrayList<Link>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			arr.add(new Link(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(arr, new Comparator<Link>(){
			@Override
			public int compare(Link o1, Link o2) {
				return Integer.compare(o1.weight, o2.weight);
			}
		});
		
		int sum = 0;
		int last = 0;
		int[] set = new int[N];
		for(int i = 0; i < N; i++) {
			set[i] = i;
		}
		
		
		for(int i = 0; i < M; i++) {
			Link link = arr.get(i);
			
			if(getParent(set, link.a-1) != getParent(set, link.b-1)) {
				union(set, link.a-1, link.b-1);
				sum += link.weight;
				last = link.weight;
				
			};
		}
		

		System.out.println(sum-last);
		
	}
	
	static int getParent(int[] set, int a) {
		if(set[a] == a) return a;

		return set[a] = getParent(set, set[a]);
	}
	
	static void union(int[] set, int a, int b) {
		a = getParent(set, a);
		b = getParent(set, b);
		
		if(a < b) set[b] = a;
		else set[a] = b;
	}
	static class Link{
		int a;
		int b;
		int weight;
		
		public Link(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.weight = w;
		}
	}
}