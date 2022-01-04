package BOJ;

import java.io.*;
import java.util.*;


class Main{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NM = br.readLine().split(" ");
		
		int N = Integer.parseInt(NM[0]);
		int K = Integer.parseInt(NM[1]);
		
		int[] arr = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int idx = 1;
		while(st.hasMoreTokens()) {
			arr[idx] = Integer.parseInt(st.nextToken());
			idx++;
		}
		
		int[] DP = new int[N+1];
		
		for(int i = 1; i < DP.length; i++) {
			DP[i] = arr[i]+DP[i-1];
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = K; i < DP.length; i++) {
			if(max < DP[i]-DP[i-K]) {
				max = DP[i]-DP[i-K];
			}
		}
		
		System.out.println(max);
		
		
		
	}
}