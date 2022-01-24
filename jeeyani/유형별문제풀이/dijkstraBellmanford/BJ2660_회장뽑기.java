package 유형별문제풀이.dijkstraBellmanford;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 플로이드–와샬 문제
 * 
 * A -> B 로 이동할때
 * 1번만에 이동가능하면 1점
 * 2번만에 이동가능하면 2점
 * 3번만에 이동가능하면 3점.....
 * 
 @author Jeeyani
 */

public class BJ2660_회장뽑기 {

	static int n;
	static int[][] friend;
	static int[] score; //각 후보자들의 점수
	static final int INF = 987654321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		friend = new int[n + 1][n + 1];
		score = new int[n + 1];

		//friend 무한대로 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					friend[i][j] = INF;
				}
			}
		}

		int x = 0;
		int y = 0;
		while (true) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			if (x == -1 && y == -1)
				break;

			friend[x][y] = friend[y][x] = 1;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (friend[i][j] > friend[i][k] + friend[k][j]) {
						friend[i][j] = friend[i][k] + friend[k][j];
					}
				}
			}
		}

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		//회장후보의 점수 구하기
		int leaderScore = INF;
		for (int i = 1; i <= n; i++) {
			int max = 0;
			for (int j = 1; j <= n; j++) {
				if (friend[i][j] != INF) {
					max = Math.max(max, friend[i][j]); //각 후보자 마다 가장 큰 점수 구하기
				}
			}
			score[i] = max; //각 후보자의 점수 구하기
			leaderScore = Math.min(leaderScore, max);
		}

		//회장후보의 수
		int leaderNum = 0;
		for (int i = 1; i <= n; i++) {
			if (score[i] == leaderScore) {
				leaderNum++;
				sb2.append(i + " ");
			}
		}

		sb1.append(leaderScore + " " + leaderNum + "\n");

		bw.write(sb1.toString());
		bw.write(sb2.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
