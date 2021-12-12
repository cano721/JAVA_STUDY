package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int[][] map;
		int[] drow = {1, 0, -1, 0};
		int[] dcol = {0, 1, 0, -1};
		
		boolean[][] isvisited;
		
		int cnt = 0;
		
		
		while(true) {
			cnt++;
			int N = Integer.parseInt(br.readLine());
			
			if(N == 0) break;
			
			
			// 1. 입력 받기
			map = new int[N][N];
			isvisited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 2. BFS
			PriorityQueue<Link> pq = new PriorityQueue<>(new Comparator<Link>() {
				@Override
				public int compare(Link o1, Link o2) {
					return Integer.compare(o1.money, o2.money);
				}
			});
			
			pq.add(new Link(0, 0, map[0][0]));
			isvisited[0][0] = true;
			
			while(true) {
				Link now = pq.poll();
				
				if(now.row == N-1 && now.col == N-1) {
					bw.write("Problem"+" "+cnt+": "+now.money+"\n");
					break;
				}
				
				for(int i = 0; i < 4; i++) {
					int tR = now.row + drow[i];
					int tC = now.col + dcol[i];
					
					if(tR < 0 || N <= tR || tC < 0 || N <= tC || isvisited[tR][tC]) continue;
					
					pq.add(new Link(tR, tC, now.money+map[tR][tC]));
					isvisited[tR][tC] = true;
				}
			}
		}
		
		bw.flush();
	}
	static class Link{
		int row;
		int col;
		int money;
		
		public Link(int r, int c, int m) {
			this.row = r;
			this.col = c;
			this.money = m;
		}
	}
}	
