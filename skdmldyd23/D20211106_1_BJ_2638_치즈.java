import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class D20211106_1_BJ_2638_치즈 {
	private static int N, M;
	private static int[] rangeX = {-1, 0, 1, 0};
	private static int[] rangeY = {0, 1, 0, -1};
	private static int[][] map;
	private static boolean[][] visited;
	private static ArrayList<Cheese> cheeseList;
	private static int cnt;
	
	private static class Cheese {
		int x;
		int y;
		
		Cheese(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cheeseList = new ArrayList<Cheese>();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheeseList.add(new Cheese(i, j));
					++cnt;
				} 
			}
		}
		int answer = 0;
		while(cnt != 0) {
			++answer;
			visited = new boolean[N][M];
			dfs(0, 0);
			
			for(int i = 0; i < cheeseList.size(); i++) {
				int x = cheeseList.get(i).x;
				int y = cheeseList.get(i).y;
				int count = 0;

				for(int j = 0; j < 4; j++) {
					int nowX = x + rangeX[j];
					int nowY = y + rangeY[j];

					if(map[nowX][nowY] == -1) ++count;
				}

				if(count >= 2) {
					map[x][y] = 0;
					--cnt;
					cheeseList.remove(i);
					--i;
				}
			}
		}
		System.out.println(answer);
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		map[x][y] = -1; 

		for (int i = 0; i < 4; ++i) {
			int nowX = x + rangeX[i];
			int nowY = y + rangeY[i];

			if (nowX < 0 || nowY < 0 || nowX >= N || nowY >= M) continue;
			if (visited[nowX][nowY] || map[nowX][nowY] == 1) continue;

			dfs(nowX, nowY); 
		}
	}
}
