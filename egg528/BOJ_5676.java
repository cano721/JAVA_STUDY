package BOJ;

import java.util.*;
import java.io.*;


class Main{
	static int[] tree;
	static int[] arr;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		String input = "";
		
		while((input = br.readLine()) != null) {
			st = new StringTokenizer(input);
			
			StringBuilder sb = new StringBuilder("");
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			tree = new int[N*4];
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp < 0) {
					arr[i] = -1;
				}else if(temp == 0) {
					arr[i] = 0;
				}else {
					arr[i] = 1;
				}
			}
			
			
			init(0, N-1, 1);
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				String order = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(order.equals("P")) {
					int temp = get(0, N-1, 1, a-1, b-1);
					if(temp == 0) {
						sb.append("0");
					}else if(temp == -1) {
						sb.append("-");
					}else {
						sb.append("+");
					}
				}else {
					if(b < 0) {
						b = -1;
					}else if(b == 0) {
						b = 0;
					}else {
						b = 1;
					}
					arr[a-1] = b;
					update(0, N-1, 1, a-1, b);
				}
			}
			
			System.out.println(sb.toString());
		}
	}
	static int init(int start, int end, int node) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start+end)/2;
		return tree[node] = init(start, mid, node*2) * init(mid+1, end, node*2+1);
	}
	static int get(int start, int end, int node, int left, int right) {
		if(end < left || right < start) return 1;
		
		if(left <= start && end <= right) return tree[node];
		
		int mid = (start + end) / 2;
		
		return get(start, mid, node*2, left, right) * get(mid+1, end, node*2+1, left, right);
	}
	static int update(int start, int end, int node, int index, int element) {
		if(index < start || end < index) return tree[node];
		
		
		if(start == end) return tree[node] = element;
		
		int mid = (start + end) / 2;
		
		return tree[node] = update(start, mid, node*2, index, element) * update(mid+1, end, node*2+1, index, element);
		
	}	
}