package 전체유형문제풀이;

import java.util.*;

public class PG17679_프렌즈4블록 {

	public static void main(String[] args) {

		int m = 4;
		int n = 5;
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };

		int result = solution(m, n, board);

		System.out.println(result);

	}

	static String[][] map;

	public static int solution(int m, int n, String[] board) {
		map = new String[m][n];
		for (int i = 0; i < m; i++) {
			map[i] = board[i].split("");
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		

		int answer = 0;

		while (true) {

			int blockCnt = chkBlock(m, n);

			if (blockCnt == 0)
				break;

			answer += blockCnt;
			delBlock(m, n);

		}
		return answer;
	}

	//정사각형으로 이루어진 블록 찾기
	public static int chkBlock(int m, int n) {
		boolean[][] tempB = new boolean[m][n];

		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {

				if (!map[i][j].equals(".")) {

					String cur = map[i][j];
					//정사각형 확인
					if (cur.equals(map[i - 1][j - 1]) && cur.equals(map[i - 1][j]) && cur.equals(map[i][j - 1])) {
						tempB[i - 1][j] = true;
						tempB[i - 1][j - 1] = true;
						tempB[i][j - 1] = true;
						tempB[i][j] = true;
					}
				}
			}
		}

		return cntBlock(m, n, tempB);

	}

	//정사각형 갯수 체크이후, 삭제하기
	public static int cntBlock(int m, int n, boolean[][] temp) {
		int cnt = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (temp[i][j]) {
					cnt++;
					map[i][j] = ".";
				}
			}
		}
		return cnt;
	}

	//블록 아래로 내린 후, board 재 구성 하기
	public static void delBlock(int m, int n) {

		for (int i = m - 1; i >= 0; i--) {
			for (int j = 0; j < n; j++) {

				//빈블록인 경우 아래로 내리기
				if (map[i][j] == ".") {

					for (int k = i - 1; k >= 0; k--) {
						if (map[k][j] != ".") {
							map[i][j] = map[k][j];
							map[k][j] = ".";
							break;
						}
					}
				}
			}
		}
	}
}