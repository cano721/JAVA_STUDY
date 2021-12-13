package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		while(true) {
			String[] temp = br.readLine().split(" ");
			
			int a = Integer.parseInt(temp[0])-1;
			int b = Integer.parseInt(temp[1])-1;
			
			if(a == -2 && b == -2) break;
			
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		
		// 문제 핵심! - a to a == 0과 a to b == 0을 구분해야 한다.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i != j && map[i][j] == 0) map[i][j] = 100000000;
			}
		}
		
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		
		int[] cnt = new int[N];
		int real_min = 100;
		
		for(int i = 0; i < N; i++) {
			int max = 1;
			for(int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
			cnt[i] = max;
			real_min = Math.min(real_min, max);
		}
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i = 0; i < N; i++) {
			if(cnt[i] == real_min) ans.add(i+1);
		}
		
		Collections.sort(ans);
		System.out.println(real_min+" "+ans.size());
		for(int i = 0; i < ans.size(); i++) {
			System.out.print(ans.get(i)+" ");
		}
	}
}	
