package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2644_촌수계산 {
	static int n, m, p1, p2, answer = -1;
	static int[][] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()); 
		
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()); 

		m = Integer.parseInt(st.nextToken());

		visited = new boolean[m];
		arr = new int[m][2];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		//구현
		solution(0, 0);
		//출력
		System.out.println(answer);
	}

	private static void solution(int cnt, int depth) {
		visited[depth] = true;
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) continue;
			if(p1 == i || p2 == i) {
				answer = cnt+1;
				return;
			}
			solution(cnt+1, i);
		}
	}

}
