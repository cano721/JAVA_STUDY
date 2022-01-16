package BOJ;

import java.util.*;
import java.lang.ref.PhantomReference;
import java.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static int n;
	static int[][] map;
	static int[][] dp;
	static final int INF = 11000000;
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		map = new int[n][n];
		
		dp = new int[n][(1 << n) - 1];	
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int weight = Integer.valueOf(st.nextToken());
				map[i][j] = weight;
			}
		}
		

		for(int i = 0; i < n; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		System.out.println(dfs(0, 1));
	}
	

	private static int dfs(int city, int visitBitmask) {
		
		if(visitBitmask == (1 << n) - 1) {	
			if(map[city][0] == 0) {	
				return INF;
			}
			
			
			return map[city][0];
		}
		
		if(dp[city][visitBitmask] != INF) {
			return dp[city][visitBitmask];
		}
		
		for(int i = 0; i < n; i++) {	
			if((visitBitmask & (1 << i)) == 0 && map[city][i] != 0) {	
				dp[city][visitBitmask] = Math.min(dp[city][visitBitmask], dfs(i, visitBitmask | (1 << i)) + map[city][i]);	// dfs(다음 도시, 다음도시 방문했다고 가정) + 여기서 다음 도시까지의 거리 와 최소거리 비교
			}
		}
		
		return dp[city][visitBitmask];
	}
}