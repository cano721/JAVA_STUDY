package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


/*
 * https://kth990303.tistory.com/39
 * 
 * [트리 DP]
 * 서브트리의 크기를 리프노드에서부터 계속 갱신해줌
 * 
 * 1. 노드가 아닌, "간선"의 관점으로 생각하기
 * 2. u,v 간선의 사용횟수 = (전체에서 2개의 노드를 선택하는 경우의 수) - (parent(u,v)를 포함한 서브트리의 크기에서 2개의 노드를 선택하는 경우의 수)
 * 3. dfs 이용하여 서브트리 크기 구하기 (루트노드는 중복됨에 유의)
 * 
 * */

public class BJ20188_등산마니아 {
	
	static ArrayList<Integer>[] list;
	static int n;
	static int ans=0;
	static int[] dp;
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		dp = new int[n+1];
		
		for (int i = 1; i <=n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		
		for (int i = 1; i <n; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list[u].add(v);
			list[v].add(u);
		}
		
		getDfs(1);
		
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
	}


	private static long ret(int n) {
		
		return (long) n * (long)(n-1)/2;
	}


	public static int getDfs(int i) {
		//초기화(1번은 루트노드)
		dp[i] = 1;
		
		for(int node : list[i]) {
			
			if(dp[i] == 0) dp[i] += dp[node];
			
		}
		ans += ret(n) - ret(n-dp[i]);
		
		return dp[n];
	}



}
