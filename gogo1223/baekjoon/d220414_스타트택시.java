package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class Taxi{
	int startX, startY, endX, endY;
	public Taxi(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
}
class Node{
	int x, y, cost;
	public Node(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}
public class d220414_스타트택시 {
	static int N, M;
	static long fuel;
	static int[][] map;
	static Taxi[] passenger;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st= new StringTokenizer(br.readLine()," ");
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		passenger = new Taxi[M];
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine()," ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			passenger[i] = new Taxi(x1, y1, x2, y2);
		}
		boolean[] visit = new boolean[M];
		for (int j = 0; j < M; j++) {
			if(visit[j]) continue;
			//현재위치에서 가장 가까운 승객(여러명이면 행작은것, 열작은것) 
			int min = Integer.MAX_VALUE;
			int minIdx = 0;
			Taxi minTaxi = new Taxi(min, min, min, min);
			
			for (int i = 0; i < M; i++) {
				if(visit[i]) continue;
				visited = new boolean[N+1][N+1];
				int val = bfs(x, y, passenger[i].startX, passenger[i].startY);
				if(val == -1) continue;
				if(min > val) {
					minIdx = i;
					min = val;
					minTaxi = passenger[i];
				}else if(min == val) {
					if(minTaxi.startX > passenger[i].startX) {
						minIdx = i;
						minTaxi = passenger[i];
					}else if(minTaxi.startX == passenger[i].startX && minTaxi.startY > passenger[i].startY) {
						minIdx = i;
						minTaxi = passenger[i];
					}
				}
			}
			if(min == Integer.MAX_VALUE || fuel < min) {
				System.out.println("-1");
				return;
			}
			visit[minIdx] = true;
			//한칸이동마다 연료 -1 
			//승객에게 가는길
			if(fuel >= min) {
				fuel -= min;
				x = minTaxi.startX;
				y = minTaxi.startY;
			}
			//목적지 도착 승객을 태워 이동하면서 소모한 연료 양의 두 배가 충전
			visited = new boolean[N+1][N+1];
			int dist = bfs(x, y, minTaxi.endX, minTaxi.endY);
			if(dist == -1 || fuel < dist) {
				System.out.println("-1");
				return;
			}else if(fuel >= dist) {
				fuel += dist;
				x = minTaxi.endX;
				y = minTaxi.endY;
			}
		}
		System.out.println(fuel);
		
	}
	
	static boolean[][] visited;
	static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	
	private static int bfs(int x, int y, int destX, int destY) {
		if(x == destX && y == destY) return 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(x, y, 0));
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			if(n.x == destX && n.y == destY) {
				return n.cost;
			}
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if(nx < 1 || nx > N || ny < 1 || ny > N || map[nx][ny] == 1 || visited[nx][ny]) continue;
				queue.add(new Node(nx, ny, n.cost + 1));
				visited[nx][ny] = true;
			}
		}
		return -1;
	}

}
