package 유형별문제풀이.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 주사위가 구르는 것이 아닌 세로/가로 값이 계속 이동한다고 생각하자.
 * 
 * 예)
 *  가로
 *  2  1  6
 *  1  5  2
 *  5  6  1
 *  6  2  5
 * 
 */

class Dice {
	int top, up, down, left, right, bottom;

	Dice(int top, int up, int down, int left, int right, int bottom) {
		this.top = top;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.bottom = bottom;
	}
}

public class BJ14499_주사위굴리기 {

	static int n,m,x,y,k,map[][];
	static int dx[]= {0,0,0,-1,1};
	static int dy[] ={0,1,-1,0,0};

	
	public static void main(String[] args)throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		// 초기 주사위 면의 상태 - top, up, down, left, right, bottom 순서 
		Dice curDice = new Dice(0, 1, 2, 3, 4, 5); 
		int[] dice = new int[6]; // 주사위 각 면의 값을 저장할 배열 
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			int loc = Integer.parseInt(st.nextToken());

			// 굴리기 전 현재 주사위 면의 상태 
			int t = curDice.top;
			int u = curDice.up;
			int d = curDice.down;
			int l = curDice.left;
			int r = curDice.right;
			int b = curDice.bottom;

			Dice nextDice;

			// 방향을 상좌하우 0 1 2 3 순서로 만들고, nextDice에 굴린 후의 주사위 면의 상태 저장 
			if(loc == 1) { // 오른쪽 방향으로 굴릴 경우 
				loc = 3;
				nextDice = new Dice(l, u, d, b, t, r);
			} else if(loc == 2) { // 왼쪽 방향으로 굴릴 경우 
				loc = 1;
				nextDice = new Dice(r, u, d, t, b, l);
			} else if(loc == 3) { // 위쪽 방향으로 굴릴 경우 
				loc = 0;
				nextDice = new Dice(u, b, t, l, r, d);
			} else { // 아래쪽 방향으로 굴릴 경우 
				loc = 2;
				nextDice = new Dice(d, t, b, l, r, u);
			}

			// 굴러갈 위치 
			int nx = x + dx[loc];
			int ny = y + dy[loc];

			// 범위를 벗어나는 경우 -> 해당 명령 무시 
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) { 
				continue;
			}

			// 굴릴 수 있는 경우, 현재 주사위를 굴린 후의 주사위 상태로 변경 
			curDice = nextDice;
			x = nx; // 주사위 위치도 변경 
			y = ny;

			// 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
			if(map[nx][ny] == 0) { 
				map[nx][ny] = dice[nextDice.bottom];
				System.out.println(dice[nextDice.top]);
			} else { // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며,
				dice[nextDice.bottom] = map[nx][ny];
				map[nx][ny] = 0; // 칸에 쓰여 있는 수는 0 
				System.out.println(dice[nextDice.top]);
			}
		}
	}

}
