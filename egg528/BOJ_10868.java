package BOJ;

import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
	static int[] data;
	static int[] minTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		data = new int[N];
		for(int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(br.readLine());
		}
		
		minTree = new int[N*4];
		init(0, N-1, 1);
		
		for(int i = 0; i < M; i++) {
			String[] temp = br.readLine().split(" ");
			
			bw.write(getMin(0, N-1, 1, Integer.parseInt(temp[0])-1, Integer.parseInt(temp[1])-1) + "\n");
		}
		
		bw.flush();
	}
	static int init(int s, int e, int n) {
		if(s == e) return minTree[n] = data[s];
		int m = (s+e)/2;
		return minTree[n] = Math.min(init(s, m, n*2), init(m+1, e, n*2+1));
	}
	
	static int getMin(int s, int e, int n, int L, int R) {
		if(R < s || e < L) return Integer.MAX_VALUE;
		if(L <= s && e <= R) return minTree[n];
		int m = (s + e) / 2;
		
		return Math.min(getMin(s, m, n*2, L, R), getMin(m+1, e, n*2+1, L, R));
	}
	static void update(int s, int e, int n, int index, int data) {
		if(index < s || e < index) return;
		
		minTree[n] = Math.min(minTree[n], data);
		
		if(s == e) return;
		
		int m = (s+e)/2;
		update(s, m, n*2, index, data);
		update(m, e, n*2+1, index, data);
	}
}	