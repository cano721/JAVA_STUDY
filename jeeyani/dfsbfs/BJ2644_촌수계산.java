package dfsbfs;

import java.util.*;

public class BJ2644_촌수계산 {

	static int n;
	static int m;
	static int[][] familyLink;
	static int[] visited;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		m = sc.nextInt();
		
		familyLink = new int[n+1][n+1];
		visited = new int[n+1];
		
		/*
		 * 그래프 형식으로 가족연결관계를 저장
		 */
		for (int i = 0; i < m; i++) {
			
			int x = sc.nextInt();
			int y = sc.nextInt();
			familyLink[x][y] = 1;
			familyLink[y][x] = 1;

		}
		
		getVillagerBFS(a,b);
		if(visited[b]==0) {
			System.out.println(-1);
		}else {
			System.out.println(visited[b]);
		}
		
		
	}

	private static void getVillagerBFS(int start, int end) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		
		while (!q.isEmpty()) {
			
			int x = q.poll();
			
			//원하는 가족노드에 도달하면 종료
			if(x==end) break;
			
			for (int i = 1; i <= n; i++) {
				
				if(visited[i]==0 && familyLink[x][i]==1) {
					visited[i] = visited[x]+1;
					q.offer(i);
				}
				
			}
			
		}
		
		
	}

}
