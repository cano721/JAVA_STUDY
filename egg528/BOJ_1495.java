package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	static int N, S, M;
	static int[] V;
	static int ans = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		V = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] DP = new boolean[N+1][M+1];
		
		DP[0][S] = true;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M+1; j++) {
				if(DP[i-1][j]) {
					if(0 <= j - V[i-1]) DP[i][j - V[i-1]] = true;
					if(j + V[i-1] <= M) DP[i][j + V[i-1]] = true;
				}
			}
		}
		
		for(int i = M; 0 <= i; i--) {
			if(DP[N][i]) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(-1);
		return;
	}
}	