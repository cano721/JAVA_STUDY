import java.io.*;
import java.util.*;

public class Main {
	static int n,m,min;
	static int[][] map, dist;
	static int[] dr = {0,1,0,-1}; // 오,아,왼,위
	static int[] dc = {1,0,-1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
        
		m = Integer.parseInt(st.nextToken());
		n= Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		dist = new int[n][m];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			String str[] = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		bfs();

		System.out.println(min);
	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visit = new boolean[n][m];
		q.offer(new Node(0,0));
        
		visit[0][0] = true;
		dist[0][0] = 0;
        
		while (!q.isEmpty()) {
			Node node = q.poll();
			//print();
			if (node.r == n-1 && node.c == m-1) {
				min = Math.min(min, dist[n-1][m-1]);
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				
				if (!visit[nr][nc] || dist[nr][nc] > dist[node.r][node.c] + map[nr][nc]) {
					dist[nr][nc] = dist[node.r][node.c] + map[nr][nc];
					q.offer(new Node(nr,nc));
					visit[nr][nc] = true;
				}
			}
		}
	}
	
	static class Node {
		int r,c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

}