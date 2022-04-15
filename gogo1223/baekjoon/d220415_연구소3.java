package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class d220415_연구소3 {
	static class Virus{
	int x, y, time;
	public Virus(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}
	static int N, M, blank = 0;
	static int[][] map;
	static int[] combi;
	static boolean[][] visited;
	static ArrayList<Virus> virusList = new ArrayList<>();
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusList.add(new Virus(i, j, 1));
				}else if(map[i][j] == 0) {
					blank++;
				}
			}
		}
		if(blank == 0) {
			System.out.println(0);
			return;
		}
		combi = new int[M];
		combination(0, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		System.out.println(min);
	}

	private static void combination(int cnt, int cur) {
		if(cnt == M) {
			bfs();
			return;
		}
		for (int i = cur; i < virusList.size(); i++) {
			combi[cnt] = i;
			combination(cnt+1, cur+1);
		}
	}

	private static void bfs() {
		Queue<Virus> q = new LinkedList<>();
		visited = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			Virus v = virusList.get(combi[i]);
			q.add(v);
			visited[v.x][v.y] = true;
		}
		int time = 0, count = 0;
		while(!q.isEmpty()) {
			Virus v = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = v.x + dx[i];
				int ny = v.y + dy[i];
				time = v.time;
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == 1) continue;
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.add(new Virus(nx, ny, v.time+1));
					count++;
				}
				else if(map[nx][ny] == 2) {
					visited[nx][ny] = true;
					q.add(new Virus(nx, ny, v.time+1));
				}
			}
			if(count == blank) {
				time++;
				break;
			}
		}
		if(count != blank) return;
		min = Math.min(min, time-1);
	}

}
