import java.io.*;
import java.util.*;

/*
다익스트라
n = 0 이면 종료
도둑루피를 최소한으로 만나는 경로로 동글 끝까지 도달
*/
public class Main {

	static int n;
	static int[][] map;
	static boolean[][] visit;
    
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	static int[][] cost;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>((e1, e2) -> e1.m - e2.m); 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
        int cnt = 1; 
		
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			
			map = new int[n][n];
			visit = new boolean[n][n];
			cost = new int[n][n]; 

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					cost[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dijkstra();	
			System.out.println("Problem " + cnt + ": " + cost[n-1][n-1]);
			cnt++;
			
		}
	}
	
	static void dijkstra() {
		visit[0][0] = true;
		cost[0][0] = map[0][0];
		pq.offer(new Node(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			Node e = pq.poll();
			for (int i = 0; i < 4; i++) {
				int ny = e.y+ dy[i];
				int nx = e.x+ dx[i];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || visit[ny][nx]) continue;
				
				if(e.m + map[ny][nx] < cost[ny][nx]) { 
					visit[ny][nx] = true; 
					cost[ny][nx] = e.m + map[ny][nx];
					pq.offer(new Node(ny, nx, cost[ny][nx]));
					//cost 배열 0,0 에서 도착지까지 가는 최저비용 기록
				}
			}
		}
	}

	static class Node{
		int y;
		int x;
		int m;
		
		public Node(int y, int x, int m) {
			this.y = y;
			this.x = x;
			this.m = m;
		}
	}

}

