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
		
		int[] set = new int[N+1];
		for(int i = 0; i <= N; i++) {
			set[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			if(st.nextToken().equals("0")) {
				union(set, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				
			}else {
				if(getParent(set, Integer.parseInt(st.nextToken())) == getParent(set, Integer.parseInt(st.nextToken()))) {
					bw.write("YES"+"\n");
				}else {
					bw.write("NO"+"\n");
				}
			}
		}
		
		bw.flush();
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
}	