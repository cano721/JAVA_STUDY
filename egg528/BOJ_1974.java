package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			map[i][i] = 1;
		}
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 0 && map[i][k] != 0 && map[k][j] != 0) {
						map[i][j] = 1;
					}
				}
			}
		}
		
		String[] route = br.readLine().split(" ");
		for(int i = 0; i < M-1; i++) {
			if(map[Integer.parseInt(route[i])-1][Integer.parseInt(route[i+1])-1] == 0) {
				System.out.println("NO");
				return; 
			}
		}
		
		System.out.println("YES");
		
	}
}	