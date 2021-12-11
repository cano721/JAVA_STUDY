package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		ArrayList<ArrayList<Link>> arr = new ArrayList<ArrayList<Link>>();
		
		for(int i = 0; i <= N; i++) {
			arr.add(new ArrayList<Link>());
		}
		
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			arr.get(a).add(new Link(b, w));
		}
		
		long[] len = new long[N+1];
		Arrays.fill(len, Integer.MAX_VALUE);
		len[1] = 0;
		
		for(int i = 0; i < N; i++) {
			
			boolean isUpdated = false;
			for(int j = 1; j <= N; j++) {
				for(Link link : arr.get(j)) {
					if(len[j] != Integer.MAX_VALUE && len[j]+link.weight < len[link.to]) {
						isUpdated = true;
						len[link.to] = len[j]+link.weight;
					}
				}
			}
			
			if(!isUpdated) {
				break;
			}
		}
		
		
		for(int i = 1; i <= N; i++) {
			for(Link link : arr.get(i)) {
				if(Integer.MAX_VALUE != len[i] && len[i]+link.weight < len[link.to]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		for(int i = 2; i <= N; i++) {
			if(len[i] == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(len[i]);
		}

		
	}
	static class Link{
		int to;
		int weight;
		
		public Link(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}	