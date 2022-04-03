package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * [구현]
 * 
 * 1. 상하좌우 방향으로 먼지 확산시키기
 * 2. 반시계방향, 시계 방향으로 청소기 돌리기

 * 
 * 
 * */


public class BJ17144_미세먼지안녕 {

	static int n,m,t;
    static int[][] map; 
    static List<Integer> cleaner = new ArrayList<>(); 
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				//청소기의 행위치 확인해두기
				if(map[i][j] == -1) cleaner.add(i);
			}
		}
		
		while(t-- > 0) {
			
			//1. 미세먼지 확산
			getSpreadDust();
			
			//2. 공기청정기 돌리기
			getClear();
			
		}
		
		//남은 미세먼지 합 구하기
		int result = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] > 0) result +=map[i][j];
			}
		}
		
	     System.out.println(result);
		
	}

	public static void getClear() {
		//1. 반시계 반향
		int topX = cleaner.get(0);
		
		//왼쪽줄
		for(int x = topX-1; x > 0; x--) {
			map[x][0] = map[x-1][0];
		}
		
		//윗줄
		for(int y = 0; y < m-1; y++) {
			map[0][y] = map[0][y+1];
		}
		
		//오른쪽줄
		for(int x = 1; x <= topX; x++) {
			map[x-1][m-1] = map[x][m-1];
		}
		
		//아랫줄
		for(int y = m-1; y> 1; y--) {
			map[topX][y] = map[topX][y-1];
		}
		
		map[topX][1] = 0;
		
		
		//시계반향
		int bottomX = cleaner.get(1);
		
		//왼쪽줄
		for(int x = bottomX+1; x < n-1; x++) {
			map[x][0] = map[x+1][0];
		}
		//아랫줄
		for(int y = 0; y < m-1; y++) {
			map[n-1][y] = map[n-1][y+1];
		}
		//오른쪽줄
		for(int x = n-1; x >= bottomX; x--) {
			map[x][m-1] = map[x-1][m-1];
		}
		//윗줄
		for(int y = m-1; y > 1; y--) {
			map[bottomX][y] = map[bottomX][y-1];
		}
		
		map[bottomX][1] = 0;
		
	}

	public static void getSpreadDust() {
		//퍼트린 먼지 양 담기
		int[][] dustNew = new  int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				
				if(map[i][j] > 0) {
					int spreadDust = map[i][j]/5;
					int dustCnt = 0;
					
					for (int k = 0; k < 4; k++) {
						
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						//청소기인 경우 무시, 범위를 벗어나는 경우 무시
						if(nx < 0 || nx >=n || ny < 0 || ny >=m) continue;
						if(map[nx][ny] == -1) continue;
						
						dustNew[nx][ny] += spreadDust;
						dustCnt++;
					}
					
					//퍼진양만큼 남겨진 먼지양 구하기
					map[i][j] -= dustCnt*spreadDust;
				}
				
			}
		}
		
		//퍼진 먼지 갱신하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] += dustNew[i][j];
			}
		}
		
	}
	
}
