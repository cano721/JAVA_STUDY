package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class baekjoon21609_상어_중학교{	
	static int N,M, answer;
	static int[][] map;
	static boolean[][] visited;
	static LinkedList<Block> list = new LinkedList<>();
	static Queue<int []> queue = new LinkedList<int[]>();
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	
	static private class Block implements Comparable<Block>{
		int totalPoint, rainbowPoint, row, col;
		public Block(int totalPoint, int rainbowPoint, int row, int col) {
			this.totalPoint = totalPoint;
			this.rainbowPoint = rainbowPoint;
			this.row = row;
			this.col = col;
		}
		
		public int compareTo(Block o) {
			if(this.totalPoint == o.totalPoint) {
				if(this.rainbowPoint == o.rainbowPoint) {
					if(this.row == o.row) {
						return o.col - this.col;
					}
					return o.row - this.row;
				}
				return o.rainbowPoint - this.rainbowPoint;
			}
			
			return o.totalPoint - this.totalPoint;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			// 블록 그룹 찾기 2 이상 아니면 break;
			visited = new boolean[N][N];
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j< N ; j++) {
					if(map[i][j] > 0 && visited[i][j] == false) {
						bfs(i, j, true);
					}
				}
			}
			if(list.isEmpty()) break;
			
			Collections.sort(list);
			// 찾은 블록 없애기
			visited = new boolean[N][N];
			bfs(list.get(0).row, list.get(0).col, false);
			removeBlock();

			// 중력
			gravity();
			// 반시계
			map = rotate();
			// 중력 
			gravity();
			
			list.clear();
		}
		System.out.println(answer);
	}

	private static int[][] rotate() {
		int[][] temp = new int[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				temp[N-j-1][i]=map[i][j];
			}
		}
		
		return temp;
	}

	private static void gravity() {
		for(int j = 0 ; j < N ; j++) {
			for(int i = N-1 ; i >= 0 ; i--) {
				for(int k = i ; k < N-1 ; k ++) {
					if(map[k][j] == -1) continue;
					if(map[k][j] != -2 && map[k+1][j] == -2) {
						int temp = map[k][j];
						map[k][j] = -2;
						map[k+1][j] = temp;
					}
				}
			}
		}
	}

	private static void removeBlock() {
		int count = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(visited[i][j] == true) {
					count++;
					map[i][j] = -2;
				}
			}
		}
		
		answer += (int)Math.pow(count, 2);
	}

	private static void bfs(int x, int y, boolean flag) {
		int blockPoint = map[x][y];
		visited[x][y] = true;
		queue.offer(new int[]{x, y});
		int totalPoint = 1;
		int rainbowPoint = 0;
		while(! queue.isEmpty()) {
			int cur[] = queue.poll();
			for(int i  = 0 ; i < 4 ; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(0 > nx || nx >= N || 0 > ny || ny >= N || visited[nx][ny] == true) continue;
				if(map[nx][ny] == blockPoint || map[nx][ny] == 0) {					
					if(map[nx][ny] == 0) rainbowPoint += 1;
					totalPoint += 1;
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
		
		if(totalPoint >= 2) list.add(new Block(totalPoint, rainbowPoint, x, y));
		if(flag == true) {
			for(int i = 0; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] == 0) visited[i][j] = false;
				}
			}
		}
		
	}
}