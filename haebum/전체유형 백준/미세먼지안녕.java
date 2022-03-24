/**
 * 1. 전체 맵을 돌며, 미세먼지 확산
 * 
 * 2. 공청기 돌리기
 */

import java.util.*;
import java.io.*;

public class 미세먼지안녕 {

    public static int r,c,t;
    public static int[][] map;
    public static ArrayList<Integer> cleaner = new ArrayList<>();
    public static int[][] dirXY = {{0,1},{-1,0},{0,-1},{1,0}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    cleaner.add(i);
                }
            }
        }

        for(int i = 0; i < t; i++){
            spread();
            clean();
        }

        int answer = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0){
					answer += map[i][j];
                }
			}
		}
		System.out.println(answer);
    }

    public static void clean() {
		int x = cleaner.get(0);
        int y = 0;

		for (int i = x; i > 0; i--){
			map[i][0] = map[i - 1][0];
        }
		for (int i = 1; i < c; i++){
			map[0][i - 1] = map[0][i];
        }
		for (int i = 1; i <= x; i++){
			map[i - 1][c - 1] = map[i][c - 1];
        }
		for (int i = c - 1; i > 0; i--){
			map[x][i] = map[x][i - 1];
        }
		map[x][y] = -1;
		map[x][y + 1] = 0;

		x = cleaner.get(1);
        y = 0;

		for (int i = x + 1; i < r; i++){
			map[i - 1][0] = map[i][0];
        }
		for (int i = 1; i < c; i++){
			map[r - 1][i - 1] = map[r - 1][i];
        }
		for (int i = r - 1; i > x; i--){
			map[i][c - 1] = map[i - 1][c - 1];
        }
		for (int i = c - 1; i > 0; i--){
			map[x][i] = map[x][i - 1];
        }
		map[x][y] = -1;
		map[x][y + 1] = 0;
	}

    public static void spread(){

        int[][] addDust = new int[r][c];

        // 먼지 퍼트리기
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(map[i][j] > 0){
                    int cnt = 0; // 퍼트린 방 개수
                    int newDust = map[i][j]/5; // 퍼트릴 먼지양
                    for(int idx = 0; idx < 4; idx++){
                        int x = i + dirXY[idx][0];
                        int y = j + dirXY[idx][1];

                        if(x < 0 || x >= r || y < 0 || y >= c){
                            continue;
                        }

                        if(map[x][y] == -1){
                            continue;
                        }
                        addDust[x][y] += newDust;
                        cnt++;
                    }

                    map[i][j] -= newDust*cnt;
                }

            }
        }

        // 퍼트린 먼지 더하기
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                map[i][j] += addDust[i][j];
            }
        }
    }
}
