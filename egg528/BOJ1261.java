package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	
	static int answer;
	static int[] drow = {0, 1, 0, -1};
	static int[] dcol = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] NM = br.readLine().split(" ");
		int M = Integer.parseInt(NM[0]);
		int N = Integer.parseInt(NM[1]);
		
		String[][] map = new String[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().split("");
		}
		
		boolean[][] visited = new boolean[N][M];
		
		answer = N+M+1;
		
		PriorityQueue<Algo> q = new PriorityQueue<>(new Comparator<Algo>() {
			@Override
			public int compare(Algo o1, Algo o2) {
				return Integer.compare(o1.cnt, o2.cnt);
			}
		});
		q.add(new Algo(0, 0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Algo algo = q.poll();
			
			int r = algo.row;
			int c = algo.col;
			int count = algo.cnt;
			
			if(N-1 == r && M-1 == c && count < answer) {
				answer = count;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int tR = r+drow[i];
				int tC = c+dcol[i];
				
				if(tR < 0 || N <= tR || tC < 0 || M <= tC || visited[tR][tC]) continue;
				
				if(map[tR][tC].equals("1")) q.add(new Algo(tR, tC, count+1));
				else q.add(new Algo(tR, tC, count));
				
				visited[tR][tC] = true;
			}
		}
		
		System.out.println(answer);
		
		
	}
	static class Algo{
		int row;
		int col;
		int cnt;
		
		public Algo(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
}