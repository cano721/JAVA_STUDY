package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] remain = new int[M];
        long sum = 0;
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) {
        	sum += Integer.parseInt(st.nextToken());
        	remain[(int) (sum%M)]++;
        }
        
        long ans = remain[0];
        for(int i = 0; i < M; i++) {
        	ans += (long) remain[i] * (remain[i]-1) / 2;
        }
        
        System.out.println(ans);
        
	}
}