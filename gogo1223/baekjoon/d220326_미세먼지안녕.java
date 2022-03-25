package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d220326_미세먼지안녕 {
	static int R, T, C;
	static int[][] map;
	static int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};	//하, 우, 상, 좌 //상, 우, 하, 좌
	//static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};	//우, 하, 좌, 상
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        int cleanerRow = 0;
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					cleanerRow = i;
				}
			}
		}
        int num = 0;
        while(num < T) {
        	//미세먼지 확산
            int[][] spread = new int[R][C];
            for (int i = 0; i < R; i++) {
    			for (int j = 0; j < C; j++) {
    				int count = 0;	//확산 가능 방향
    				int mise = map[i][j] / 5;
    				for (int k = 0; k < 4; k++) {
    					int nx = i + dr[k], ny = j + dc[k];
    					if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) continue;
    					count++;
    					spread[nx][ny] += mise;
    				}
    				spread[i][j] += map[i][j] - mise * count;
    			}
    		}
            map = spread;
            //공기청정기 작동
            //아래부분(시계방향) 후 윗부분
            airCleanerStart(cleanerRow, true);
            airCleanerStart(cleanerRow-1, false);
            num++;
        }
        int answer = 0;
        for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == -1) continue;
				answer += map[i][j];
			}
		}
        System.out.println(answer);
	}
	private static void airCleanerStart(int cleanerRow, boolean isClockwise) {
		int changeDir = 0;
        int x = cleanerRow, y = 0;
        int idx = isClockwise ? 0 : 2;
        while(changeDir < 4) {
        	idx %= 4;
        	int nx = x + dr[idx], ny = y + dc[idx];
        	if(nx < 0 || nx >= R || ny < 0 || ny >= C
        			|| (isClockwise && nx < cleanerRow)
        			|| (!isClockwise && nx > cleanerRow)) {
        		changeDir++;
        		idx = isClockwise? idx + 1: idx + 3;
        		continue;
        	}
        	if(nx == cleanerRow && ny == 0) {
        		map[x][y] = 0;
        		break;
        	}else if(x == cleanerRow && y == 0) {
            	x = nx;
            	y = ny;
        		continue;
        	}
        	map[x][y] = map[nx][ny];
        	x = nx;
        	y = ny;
        }
	}

}
