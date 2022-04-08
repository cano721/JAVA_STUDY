package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d220409_로봇 {
	static int M, N;
	static int endX, endY, endDir;
	static int[][] map;
	static int[] dx = {-1, 0, 0, 1}, dy = {0, 1, -1, 0};	//북 동 서 남
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st= new StringTokenizer(br.readLine()," ");		
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int startDir = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");		
		endX = Integer.parseInt(st.nextToken());
		endY = Integer.parseInt(st.nextToken());
		endDir = Integer.parseInt(st.nextToken());
		
		int answer = bfs(startX, startY, startDir, 0);
		System.out.println(answer);
	}
	static int min = Integer.MAX_VALUE;
	private static int bfs(int startX, int startY, int startDir, int depth) {
		if(startX == endX && startY == endY && startDir == endDir) {
			return depth;
		}
		int value = 0;
		int nextDir = 0;
		for (int i = 0; i < 5; i++) {
			switch(i) {
			case 1: //1이동
				value = bfs(startX + dx[startDir%4], startY + dx[startDir%4], startDir, depth+1);
				break;
			case 2: //2이동
				value = bfs(startX + dx[startDir%4]*2, startY + dx[startDir%4]*2, startDir, depth+1);
				break;
			case 3: //3이동
				value = bfs(startX + dx[startDir%4]*3, startY + dx[startDir%4]*3, startDir, depth+1);
				break;
			case 4: //방향전환right
				switch(startDir) {
					case 1: nextDir = 3; break;
					case 2: nextDir = 4; break;
					case 3: nextDir = 2; break;
					case 4: nextDir = 1; break;
				}
				value = bfs(startX, startY, nextDir, depth+1);
				break;
			case 5: //방향전환left
				switch(startDir) {
					case 1: nextDir = 4; break;
					case 2: nextDir = 3; break;
					case 3: nextDir = 1; break;
					case 4: nextDir = 2; break;
				}
				value = bfs(startX, startY, nextDir, depth+1);
				break;
			}
			min = Math.min(value, min);
		}
		
		return min;
	}

}
