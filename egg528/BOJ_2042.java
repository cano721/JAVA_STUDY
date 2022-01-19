package BOJ;

import java.util.*;
import java.io.*;


class Main{
	static long[] tree;
	static long[] arr;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		tree = new long[N*4];
		arr = new long[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(0, N-1, 1);
		
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if(a == 1) {
				long diff = c - arr[b-1];
				arr[b-1] += diff;
				update(0, N-1, 1, b-1, diff);
			}else {
				bw.write(sum(0, N-1, 1, b-1, (int)c-1)+"\n");
			}
		}
		
		bw.flush();
		
	}
	static long init(int s, int e, int p) {
		if(s == e) return tree[p] = arr[s];
		
		int mid = (s + e) / 2;
		
		return tree[p] = init(s, mid, p*2) + init(mid+1, e, p*2+1);
	}
	
	static long sum(int s, int e, int node, int left, int right) {
		if(right < s || e < left) return 0;
		
		if(left <= s && e <= right) return tree[node];
		
		int mid = (s+e)/2;
		return sum(s, mid, node*2, left, right) + sum(mid+1, e, node*2+1, left, right);
	}
	
	static void update(int s, int e, int node, int index, long diff) {
		if(index < s || e < index) return;
		
		tree[node] += diff;
		
		if(s == e) return;
		int mid = (s+e) / 2;
		
		update(s, mid, node*2, index, diff);
		update(mid+1, e, node*2+1, index, diff);
	}
}