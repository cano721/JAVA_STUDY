package programmers;

import java.util.ArrayList;

public class d220310_빛의경로사이클 {

	public static void main(String[] args) {
		String[] grid = {"SL","LR"};
		int[] answer = solution(grid);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
	static int R, C;
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };	//하, 좌, 상, 우(시계방향)
	static boolean[][][] visited;
	
	private static int[] solution(String[] grid) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		R = grid.length;		//row
		C = grid[0].length();	//col
		visited = new boolean[R][C][4];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = 0; k < 4; k++) {
					if(!visited[i][j][k]) {
						answer.add(light(grid, i, j, k));
					}
				}
			}
		}
		
		return answer.stream().sorted().mapToInt(i -> i).toArray();
	}

	private static int light(String[] grid, int r, int c, int d) {
		int cnt = 0; // 이동거리
		
        while (true) {
            if (visited[r][c][d])
                break;
 
            cnt++;	// 거리증가
            visited[r][c][d] = true; // 방문처리
 
            if (grid[r].charAt(c) == 'L')
                d = (d+3) % 4; // 좌회전
            else if (grid[r].charAt(c) == 'R')
                d = (d+1) % 4; // 우회전
 
            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;
        }
 
        return cnt;
	}

}
