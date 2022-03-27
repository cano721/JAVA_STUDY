package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d220326_청소년상어 {
	static int[][][] map;
	static boolean[] eatFish;
	static boolean isSharkEat;
	static int answer = 0;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {1, 0, -1, -1, -1, 0, 1, 1}; //↗, ↑, ↖, ←, ↙, ↓, ↘, →
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[4][4][2];
        for (int i = 0; i < 4; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 2; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
        eatFish = new boolean[17];

    	//상어이동
        int eatNum = map[0][0][0];
        eatFish[map[0][0][0]] = true;
        map[0][0][0] = -1;	//상어는 -1로 표현한다.
        //물고기이동
    	changeFish();
        int sharkX = 0, sharkY = 0;
        isSharkEat = false;
        
        changeShark(sharkX, sharkY, eatNum);
        System.out.println(answer);
        
	}
	private static void changeShark(int sharkX, int sharkY, int eatNum) {
		if(isSharkEat) {
			answer = Math.max(answer, eatNum);
			return;
		}
		
		int count = 0;
		isSharkEat = false;
		for (int i = 0; i < 4; i++) {
        	int sharkDir = map[sharkX][sharkY][1] % 8;
			int nx = sharkX + dx[sharkDir]*(i+1), ny = sharkY + dy[sharkDir]*(i+1);
			if(nx < 0 || nx >= 4 || ny < 0|| ny >= 4 || map[nx][ny][0] <= 0) continue;
			int fish = map[nx][ny][0];
			int[][][] copyMap = new int[4][4][2];
			for(int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 2; l++) {
						copyMap[j][k][l] = map[j][k][l];
					}
				}
			}
			//상어 이동
			eatFish[fish] = true;
			map[sharkX][sharkY][0] = 0;
			map[nx][ny][0] = -1;
			//물고기 이동
			changeFish();
			changeShark(nx, ny, eatNum + fish);
			//원상복구
			eatFish[fish] = false;
			for(int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 2; l++) {
						map[j][k][l] = copyMap[j][k][l];
					}
				}
			}
			count++;
		}
		//전체 while문 끝내기
		if(count == 0) {
			isSharkEat = true;
		}
		
	}
	private static void changeFish() {
		for (int i = 1; i < 17; i++) {
			if(eatFish[i]) continue;	//먹힌 친구는 map에 없다.
			loop:
			for (int j = 0; j < 4; j++) {
				int count = 0;
				for (int k = 0; k < 4; k++) {
					if(map[j][k][0] == i) {
						int dir = map[j][k][1] % 8;
						int nx = j + dx[dir], ny = k + dy[dir];
						if(count == 8) continue;
						if(nx >= 4 || nx < 0 || ny >= 4 || ny < 0 || map[nx][ny][0] == -1) {
							//칸 넘거나 다음 이동 칸이 상어일 때 방향 전환
							map[j][k][1]++;
							k--;
							count++;
							continue;
						}
						int tempFish = map[j][k][0];
						int tempDir = map[j][k][1];
						map[j][k][0] = map[nx][ny][0];
						map[j][k][1] = map[nx][ny][1];
						map[nx][ny][0] = tempFish;
						map[nx][ny][1] = tempDir;
						break loop;
					}
				}
			}
		}
		
	}

}
