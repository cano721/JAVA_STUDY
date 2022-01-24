package 유형별문제풀이.dfsbfs;

import java.io.*;
import java.util.*;

/*
 * 1. 2개의 동전이 모두 맵 밖으로 떨어지는 경우 -> 해당 방향 진행 불가능
 * 2. 2개 증 1개만 떨어진 경우
 * 3. 2개 동전 모두 떨어지지 않는 경우 -> 하나만 떨어질때까지 계속 반복
 * 
 * ------------------------------------------------------
 * 
 * 1. 2개의 동전의 각 위치정보, 버튼을 누른 횟수 정보 저장
 * 2. 버튼 누른 횟수가 10회이상시 -1 출력
 * 3. 상하좌우 방향모두 탐색
 * 		- 두개 동전 같이 진행
 * 		- 벽일때 움직이지 못함
 * 4. 동전이 이동하는 범위가 유요한 범위내 인지 확인
 * 5. 떨어지는 동전이 하나 일때만 cnt++
 * 
 * 
 */

class coinNode {
	int x;
	int y;

	coinNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class coinLoc { //두 동전의 위치와 현재 버튼을 누른 횟수를 기록
	int x1;
	int y1;
	int x2;
	int y2;
	int count;

	public coinLoc(int x1, int y1, int x2, int y2, int count) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.count = count;
	}
}

public class BJ16197_두동전 {

	static int n;
	static int m;
	static char[][] board;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static coinNode[] coin;
	static boolean[][][][] visited; //각 동전의 위치를 4차원으로 표현

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		board = new char[n][m];
		coin = new coinNode[2];

		int idx = 0;
		for (int i = 0; i < n; i++) {
			String temp = sc.next();
			for (int j = 0; j < m; j++) {
				board[i][j] = temp.charAt(j);

				//코인 위치 저장
				if (board[i][j] == 'o') {
					coin[idx++] = new coinNode(i, j);
				}
			}
		}

		visited = new boolean[n][m][n][m];
		System.out.println(getCoinBFS());

	}

	private static int getCoinBFS() {
		Queue<coinLoc> q = new LinkedList<>();
		q.offer(new coinLoc(coin[0].x, coin[0].y, coin[1].x, coin[1].y, 0));
		visited[coin[0].x][coin[0].y][coin[1].x][coin[1].y] = true;

		while (!q.isEmpty()) {

			coinLoc currentLoc = q.poll();

			//이동횟수가 10회 이상이면 종료
			if (currentLoc.count >= 10) break;

			//상하좌우 방향으로 이동하기
			for (int i = 0; i < 4; i++) {
				int nx1 = currentLoc.x1 + dx[i];
				int ny1 = currentLoc.y1 + dy[i];
				int nx2 = currentLoc.x2 + dx[i];
				int ny2 = currentLoc.y2 + dy[i];

				//동전 각각을 움직여서 벽인지 혹은 판을 넘어가는지 확인하기
				if (!moveCoin(nx1, ny1)) {
					nx1 = currentLoc.x1;
					ny1 = currentLoc.y1;
				}
				if (!moveCoin(nx2, ny2)) {
					nx2 = currentLoc.x2;
					ny2 = currentLoc.y2;
				}
				
				//코인갯수
				int coinCnt = 0;
				
				//코인이 떨어지지 않고 있는지 확인
				if(nx1 >=0 && ny1 >= 0 && nx1 < n && ny1 < m) coinCnt++;
				if(nx2 >=0 && ny2 >= 0 && nx2 < n && ny2 < m) coinCnt++;
				
				//한개만 떨어진 경우 결과 리턴
				if(coinCnt == 1) {
					return currentLoc.count+1;
				}
				
				//
				else if(coinCnt == 2 && !visited[nx1][ny1][nx2][ny2]) {
					visited[nx1][ny1][nx2][ny2] = true;
					q.offer(new coinLoc(nx1, ny1, nx2, ny2, currentLoc.count+1));
				}

			}

		}

		return -1;
	}

	private static boolean moveCoin(int x, int y) {

		/*if (x < 0 || x >= n || n < 0 || n >= m || board[x][y] == '#') {
			return false;
		}
		
		return true;*/
		
		if(x >= 0 && y >= 0 && x < n && y < m && board[x][y] == '#') {
            return false;
        }
        return true;
	}

}
