package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point>{
	int x;
	int y;
	int cost;
	public Point(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
	@Override
	public int compareTo(Point o) {
		return cost - o.cost;
	}
}
public class n1261_알고스팟 {
	static int n, m;
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
        	String str = br.readLine();
        	for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(str.charAt(j-1)+"");
			}
		}
        int answer = bfs(1,1);
        System.out.println(answer);
	}

	private static int bfs(int i, int j) {
		PriorityQueue<Point> q = new PriorityQueue<>(); 
		q.offer(new Point(i, j, 0));
		boolean[][] visited = new boolean[n+1][m+1];
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			if(p.x == n && p.y == m) {
				return p.cost;
			}
			for (int k = 0; k < 4; k++) {
				int nextX = p.x + dx[k];
				int nextY = p.y + dy[k];
				if(nextX > n || nextX <= 0 
						|| nextY > n || nextY <= 0 || visited[nextX][nextY]) {
					continue;
				}
				if(map[nextX][nextY] == 0) {
					visited[nextX][nextY] = true;
					q.offer(new Point(nextX, nextY, p.cost));
				} 
				if(map[nextX][nextY] == 1) {
					visited[nextX][nextY] = true;
					q.offer(new Point(nextX, nextY, p.cost + 1));
				}
			}
		}
		
		return 0;
	}
	
}
