package elwlahd555.programmers;

public class 프로그래머스42898_등굣길 {
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = { { 2, 2 } };
		System.out.println(solution(m, n, puddles));
	}

	public static int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		int map[][] = new int[n + 1][m + 1];
		for (int i = 0; i < puddles.length; i++) {
			map[puddles[i][1]][puddles[i][0]] = -1;
		}
		for (int i = 1; i < n+1; i++) {
			if(map[i][1]==-1) {
				break;
			}
			map[i][1]=1;
		}
		for (int i = 1; i < m+1; i++) {
			if(map[1][i]==-1) {
				break;
			}
			map[1][i]=1;
		}
		for (int i = 2; i < n + 1; i++) {
			for (int j = 2; j < m + 1; j++) {
				if (map[i][j] == -1) {
					continue;
				} else {
					if(map[i - 1][j] == -1 && map[i][j - 1] == -1) {
						map[i][j]=0;
					}
					else if (map[i - 1][j] == -1) {
						map[i][j] = map[i][j - 1] % 1000000007;
					}
					else if (map[i][j - 1] == -1) {
						map[i][j] = map[i - 1][j] % 1000000007;
					}else {
						map[i][j] = map[i - 1][j] % 1000000007 + map[i][j - 1] % 1000000007;
					}
				}
			}
		}
		answer = map[n][m] % 1000000007;
		return answer;
	}
}
