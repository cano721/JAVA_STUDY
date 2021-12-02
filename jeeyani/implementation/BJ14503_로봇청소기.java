package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503_로봇청소기 {

	static int n,m,map[][],cnt=0;
	static int[] dx = {-1, 0, 1, 0}; // 북,동,남,서
    static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//--입력 종료
		
		getClean(x,y,d);
		
		System.out.println(cnt);
		
	}

	/*
	 * <왼쪽>
	 * 0 -> 3
	 * 1 -> 0
	 * 2 -> 1
	 * 3 -> 2
	 * 
	 * : (d+3)%4
	 * 
	 * 
	 * <오른쪽>
	 * 0 -> 1
	 * 1 -> 2
	 * 2 -> 3
	 * 3 -> 0
	 * 
	 * : (d+2)%4
	 * 
	 */
	
	private static void getClean(int x, int y, int d) {
		
		//현재 청소기 위치를 청소
		if(map[x][y]==0) {
			cnt++;
			map[x][y] = 2;
		}
		
		boolean chk = false;
		int realD = d;
		//왼쪽부터 이동
		for (int i = 0; i < 4; i++) {
			int nd = (d+3)%4;
			int nx = x+dx[nd];
			int ny = y+dy[nd];
			
			//이동한 곳이 범위내인 경우
			if(nx > 0 && ny>0 && nx < n && ny < m) {
				//청소를 해야하는 공간일 경우
				if(map[nx][ny] == 0) {
					getClean(nx, ny, nd);
					chk = true; //지나간 곳 체크
					break;
				}
			}
			d = (d+3)%4;
		}
		
		//모두 청소되어있거나 벽인 경우
		if(!chk) {
			int nd = (realD+2)%4;
			int nx = x+dx[nd];
			int ny = y+dy[nd];
			
			if(nx > 0 && ny>0 && nx < n && ny < m) {
				if(map[nx][ny] !=1) {
					//방향유지이후 후진
					getClean(nx, ny, realD);
				}
			}
		}
	}
}
