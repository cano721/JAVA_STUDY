package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
4 2 0 0 8
0 2
3 4
5 6
7 8
4 4 4 1 3 3 3 2
 */
public class d220319_주사위굴리기 {
	static int N, M, x, y, k;
	static int[][] arr;
	static int[] order;
	static int[] dx = {0, 0, 0, -1, 1}, dy = {0, 1, -1, 0, 0};	//동,서,북,남
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        st = new StringTokenizer(br.readLine());
        order = new int[k];
        for (int i = 0; i < k; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
        //index 1이 바닥면, 3이 윗면
        int[] diceX = new int[4];
        int[] diceY = new int[4];
        int bottom = 0, up = 0;
        
        for (int i = 0; i < k; i++) {
			int nx = x + dx[order[i]];
			int ny = y + dy[order[i]];
			if(nx >= N || nx < 0 || ny >= M || ny < 0) continue;
			bottom = arr[nx][ny];
			switch(order[i]) {
				case 1: 
					if(arr[nx][ny] == 0) {
						bottom = diceY[2];
						arr[nx][ny] = diceY[2];
					}else {
						arr[nx][ny] = 0;
					}
					diceY[2] = diceY[3];
					diceY[3] = diceY[0];
					diceY[0] = diceY[1];
					diceY[1] = bottom;
					diceX[3] = diceY[3];
					diceX[1] = bottom;
					break;//동
				case 2: 
					if(arr[nx][ny] == 0) {
						bottom = diceY[0];
						arr[nx][ny] = diceY[0];
					}else {
						arr[nx][ny] = 0;
					}
					diceY[0] = diceY[3];
					diceY[3] = diceY[2];
					diceY[2] = diceY[1];
					diceY[1] = bottom;
					diceX[3] = diceY[3];
					diceX[1] = bottom;
					break;//서
				case 3: 
					if(arr[nx][ny] == 0) {
						bottom = diceX[0];
						arr[nx][ny] = diceX[0];
					}else {
						arr[nx][ny] = 0;
					}
					diceX[0] = diceX[3];
					diceX[3] = diceX[2];
					diceX[2] = diceX[1];
					diceX[1] = bottom;
					diceY[3] = diceX[3];
					diceY[1] = bottom;
					break;//북
				case 4: 
					if(arr[nx][ny] == 0) {
						bottom = diceX[2];
						arr[nx][ny] = diceX[2];
					}else {
						arr[nx][ny] = 0;
					}
					diceX[2] = diceX[3];
					diceX[3] = diceX[0];
					diceX[0] = diceX[1];
					diceX[1] = bottom;
					diceY[3] = diceX[3];
					diceY[1] = bottom;
					break;//남
			}
			System.out.println(diceX[3]);
			x = nx;
			y = ny;
		}
	}

}
