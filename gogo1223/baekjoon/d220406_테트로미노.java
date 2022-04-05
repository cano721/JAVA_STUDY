package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class d220406_테트로미노 {
	static int N, M;
	static int[][] map;
	static int[][] dx = {
			//1
			{0, 0, 0},	//ㅡ
			{1, 2, 3},	//ㅣ
			//2
			{1, 1, 0},	//ㅁ
			//3
			{1, 2, 2},	//ㄴ
			{0, 0, -1},	//ㅢ
			{0, 1, 2},	//ㄱ
			{1, 0, 0},	//ㅣㅡ
			{0, -1, -2},
			{0, 0, 1},
			{0, 1, 2},
			{1, 1, 1},
			//4
			{1, 1, 2},
			{0, -1, -1},
			{1, 0, -1},
			{0, 1, 1},
			//5
			{0, 1, 0},
			{1, 1, 2},
			{0, 0, -1},
			{1, 0, -1}
	};
	static int[][] dy = {
			//1
			{1, 2, 3},
			{0, 0, 0},
			//2
			{0, 1, 1},
			//3
			{0, 0, 1},
			{1, 2, 2},
			{1, 1, 1},
			{0, 1, 2},
			{1, 1, 1},
			{1, 2, 2},
			{1, 0, 0},
			{0, 1, 2},
			//4
			{0, 1, 1},
			{1, 1, 2},
			{0, 1, 1},
			{1, 1, 2},
			//5
			{1, 1, 2},
			{0, 1, 0},
			{1, 2, 1},
			{1, 1, 1}
	};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Loop: for (int k = 0; k < dx.length; k++) {
					int sum = map[i][j];
					for (int h = 0; h < 3; h++) {
						int nx = i+dx[k][h];
						int ny = j+dy[k][h];
						if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue Loop;
						sum += map[nx][ny];
					}
					answer = Math.max(answer, sum);
				}
			}
		}
		System.out.println(answer);
	}

}
