package dfsbfs;

import java.util.*;

public class BJ2606_바이러스 {

	static int n;
	static int k;
	static int[][] computerLink; //연결상태를 그래프 형태로 저장
	static boolean[] visited;
	static int cnt = 0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		
		computerLink = new int[n+1][n+1];
		visited = new boolean[n+1];
		
		/*
		 * 각 컴퓨터 사이의 연결관계를 2차원 배열의 그래프 상태로 저장
		 */
		
		for (int i = 0; i < k; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			computerLink[x][y] = 1;
			computerLink[y][x] = 1;
		}
		
		getVirusBFS(1);
		System.out.println(cnt);
	}

	private static void getVirusBFS(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			
			int x = q.poll();
			
			//n까지 포함해야함!
			for (int i = 1; i <= n; i++) {
				
				//방문하지 않은 곳 && 연결되어 있는 컴퓨터
				if(!visited[i] && computerLink[x][i]==1) {
					q.offer(i);
					visited[i] = true;
					cnt++;
				}
				
			}
			
		}
		
	}

}
