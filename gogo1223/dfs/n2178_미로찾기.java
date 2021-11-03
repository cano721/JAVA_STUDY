package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2178_미로찾기 {
	
	static int n, m, answer = Integer.MAX_VALUE;
	static int[][] arr;
	static boolean[][] visited;
	static int[] p = {0, 0, -1, 1}, q = {1, -1, 0, 0}; //좌우상하
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];
		String[] s ;
		for (int i = 0; i < n; i++) {
			s = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(s[j]);
			}
		}
		visited[0][0] = true;
		solution(0, 0, 0);
		System.out.println(answer);

	}

	private static void solution(int x, int y, int cnt) {
		if(x == n && y == m) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if(x+p[i] < 0 || x+p[i] >= n || y+q[i] < 0 || y+q[i] >= m) continue;
			if(arr[x+p[i]][y+q[i]] == 0) continue;
			if(visited[x+p[i]][y+q[i]]) continue;
			
			visited[x+p[i]][y+q[i]] = true;
			solution(x+p[i], y+q[i], cnt++);
			visited[x+p[i]][y+q[i]] = false;
			
		}
		
		
	}

}
