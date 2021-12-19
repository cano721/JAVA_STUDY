package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
	
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m == 0 && n == 0) break;
			
			int sum = 0;
			ArrayList<Link> arr = new ArrayList<Link>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				sum+=w;
				
				arr.add(new Link(a, b, w));
			}
			
			Collections.sort(arr, new Comparator<Link>() {
				@Override
				public int compare(Link o1, Link o2) {
					return Integer.compare(o1.weight, o2.weight);
				}
			});
			
			int small_sum = 0;
			int[] set = new int[n];
			for(int i = 0; i < n; i++) {
				set[i] = i;
			}
			
			for(int i = 0; i < arr.size(); i++) {
				Link temp = arr.get(i);
				
				if(getParent(set, temp.a) != getParent(set, temp.b)) {
					union(set, temp.a, temp.b);
					small_sum += temp.weight;
				}
			}
			
			bw.write(sum-small_sum+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
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