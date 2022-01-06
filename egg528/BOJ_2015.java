package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] DP = new int[N+1];
        for(int i = 1; i <= N; i++) {
        	DP[i] = arr[i-1]+DP[i-1];
        }
        
        
        
        long cnt = 0;
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        
        for(int i = 1; i <= N; i++) {
        	cnt += map.getOrDefault(DP[i]-K, 0);
        	
        	map.put(DP[i], map.getOrDefault(DP[i], 0)+1);
        }
        
        
        
        System.out.println(cnt);
	}
}