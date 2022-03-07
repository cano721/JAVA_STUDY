package programmers;

import java.util.ArrayList;
import java.util.List;

public class d220307_1차프렌즈4블록 {

	public static void main(String[] args) {
		int m = 4;
		int n = 5;
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		int answer = solution(m, n, board);
		System.out.println(answer);	//14
	}
	static char[][] map;
	static int answer;
	private static int solution(int m, int n, String[] board) {
		answer = 0;
		map = new char[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}
		while(updateBlocks(m, n));
			
		return answer;
	}

	private static boolean updateBlocks(int m, int n) {
		boolean[][] visit = new boolean[m][n];
		int cnt = 0;
		//체크
		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if(map[i][j] == '0') continue;
				if(check(i, j)) {
					visit[i][j] = true; visit[i][j+1] = true;
					visit[i+1][j] = true; visit[i+1][j+1] = true;
				}
			}
		}
		//갱신
		for (int i = 0; i < n; i++) {
			List<Character> list = new ArrayList<>();
			for (int j = m-1; j >= 0; j--) {
				if(visit[j][i]) {
					cnt++;
				}else {
					list.add(map[j][i]);
				}
			}
			for (int j = m-1, k = 0; j >= 0; j--, k++) {
				if(list.size() <= k) map[j][i] = '0';
				else map[j][i] = list.get(k);
			}
		}
		answer += cnt;
		return cnt > 0 ? true : false;
	}
	//4개가 모두 같은지 체크
	private static boolean check(int i, int j) {
		char block = map[i][j];
		if(map[i][j+1] == block && map[i+1][j] == block && map[i+1][j+1] == block) return true;
		return false;
	}
}
