package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        while(T-- > 0) {
        	int N = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	
        	int[] arr = new int[N];
        	
        	for(int i = 0; i < N; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int[] DP = new int[N+1];
        	for(int i = 1; i <= N; i++) {
        		DP[i] = DP[i-1]+arr[i-1];
        	}
        	
        	
        	int max = Integer.MIN_VALUE;
        	for(int i = 0; i < N+1; i++) {
        		for(int j = i+1; j < N+1; j++) {
        			if(max < DP[j] - DP[i]) max = DP[j] - DP[i];
        		}
        	}
        	
        	bw.write(max+"\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
	}
}