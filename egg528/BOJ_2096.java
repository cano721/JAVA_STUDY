package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][] min = new int[N+1][3];
		int[][] max = new int[N+1][3];
		
		for(int i = 1; i <= N; i++) {
			max[i][0] = Math.max(max[i-1][0]+arr[i-1][0], max[i-1][1]+arr[i-1][0]);
			max[i][1] = Math.max(max[i-1][0]+arr[i-1][1], Math.max(max[i-1][1]+arr[i-1][1], max[i-1][2]+arr[i-1][1]));
			max[i][2] = Math.max(max[i-1][1]+arr[i-1][2], max[i-1][2]+arr[i-1][2]);
			
			min[i][0] = Math.min(min[i-1][0]+arr[i-1][0], min[i-1][1]+arr[i-1][0]);
			min[i][1] = Math.min(min[i-1][0]+arr[i-1][1], Math.min(min[i-1][1]+arr[i-1][1], min[i-1][2]+arr[i-1][1]));
			min[i][2] = Math.min(min[i-1][1]+arr[i-1][2], min[i-1][2]+arr[i-1][2]);
		}
		
		
		System.out.println(Math.max(max[N][0], Math.max(max[N][1], max[N][2])) + " " + Math.min(min[N][0], Math.min(min[N][1], min[N][2])));
		
	}
}