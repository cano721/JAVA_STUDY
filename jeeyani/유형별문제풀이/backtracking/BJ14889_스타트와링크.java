package 유형별문제풀이.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BJ14889_스타트와링크 {

	static int n;
	static int[][] power;
	static boolean[] visit;

	static int value = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		power = new int[n][n];
		visit = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < n; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combi(0, 0);
		System.out.println(value);

	}

	static void combi(int idx, int count) {
		/*종단조건*/
		// 팀 조합이 완성될 경우
		if (count == n / 2) {

			getDiff(); //최소값 찾기
			return;
		}

		for (int i = idx; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true; 
				combi(i + 1, count + 1); 
				
				visit[i] = false; // 재귀가 끝나면 비방문으로 변경
			}
		}
	}

	//두 팀의 능력차이 계산하기
	static void getDiff() {
		int startTeam = 0;
		int linkTeam = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
			
				if (visit[i] == true && visit[j] == true) {
					startTeam += power[i][j];
					startTeam += power[j][i];
				}
				else if (visit[i] == false && visit[j] == false) {
					linkTeam += power[i][j];
					linkTeam += power[j][i];
				}
			}
		}
		int val = Math.abs(startTeam - linkTeam);

		/*
		 * 차이가 0인 경우,
		 * 가장 최소의 차이임으로 바로 종료
		 */
		if (val == 0) {
			System.out.println(val);
			System.exit(0);
		}

		value = Math.min(val, value);

	}

}
