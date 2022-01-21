package BOJ;

import java.io.*;
import java.util.*;


public class Main {	
	static int[] data;
	static int[] minTree;
	static int[] maxTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		
		String[] NM = br.readLine().split(" ");
		
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		data = new int[N];
		
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		minTree = new int[N*4];
		maxTree = new int[N*4];
		
		
		init_min(0, N-1, 1);
		init_max(0, N-1, 1);
		

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(find_min(0, N-1, 1, a-1, b-1)+" "+find_max(0, N-1, 1, a-1, b-1)+"\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int init_min(int start, int end, int node) {
		if(start == end) return minTree[node] = data[start];
		int mid = (start + end) /2;
		return minTree[node] = Math.min(init_min(start, mid, node*2), init_min(mid+1, end, node*2+1));
	}
	
	static int find_min(int start, int end, int node, int left, int right) {
		if(end < left || right < start) return Integer.MAX_VALUE;
		if(left <= start && end <= right) return minTree[node];
		int mid = (start + end)/2;
		return Math.min(find_min(start, mid, node*2, left, right), find_min(mid+1, end, node*2+1, left, right));
	}
	
	
	
	static int init_max(int start, int end, int node) {
		if(start == end) return maxTree[node] = data[start];
		int mid = (start + end) /2;
		return maxTree[node] = Math.max(init_max(start, mid, node*2), init_max(mid+1, end, node*2+1));
	}
	
	static int find_max(int start, int end, int node, int left, int right) {
		if(end < left || right < start) return Integer.MIN_VALUE;
		if(left <= start && end <= right) return maxTree[node];
		int mid = (start + end)/2;
		return Math.max(find_max(start, mid, node*2, left, right), find_max(mid+1, end, node*2+1, left, right));
	}
}