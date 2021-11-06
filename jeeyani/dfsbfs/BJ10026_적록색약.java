package dfsbfs;

import java.util.Scanner;

public class BJ10026_적록색약 {

	static int n;
	static char[][] grid;
	static char[][] gridRG;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		grid = new char[n][n];
		gridRG = new char[n][n];
		visited = new boolean[n][n];
		
		//일반인이 보는 그리드판
		for (int i = 0; i < n; i++) {
			grid[i] = sc.next().toCharArray();
		}
		
		//적록색약인 사람이 보는 그리드판
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j] == 'G') {
					gridRG[i][j] = 'R';
				}else gridRG[i][j] = grid[i][j];
				
			}
		}
		
		//1. 일반인이 보는 구역의 수 구하기
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				if(!visited[i][j]) {
					getColorBlindDFS(i,j,grid);
					cnt++;
				}
				
			}
		}
		System.out.print(cnt+" ");
		
		//2. 적록색약이 보는 구역의 수 구하기
		cnt = 0;
		visited =  new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				if(!visited[i][j]) {
					getColorBlindDFS(i,j,gridRG);
					cnt++;
				}
				
			}
		}
		System.out.print(cnt);
		
	}

	private static void getColorBlindDFS(int i, int j, char[][] grid) {
		
		char temp = grid[i][j];
		visited[i][j] = true;
		
		//상하좌우 살피기
		for (int k = 0; k < 4; k++) {
			int x = i+dx[k];
			int y = j+dy[k];
			
			//범위 벗어날 경우 넘어가기
			if(x < 0 || x >= n || y < 0 || y >= n){
				continue;
			}
			//방문하지 않았고, 현재 같은 구역일 경우
			if(!visited[x][y] && grid[x][y] == temp){
				getColorBlindDFS(x, y,grid);
			}
			
		}
		
	}

}
