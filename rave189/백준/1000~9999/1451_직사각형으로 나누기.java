package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * NxM 크기의 직사각형이 주어진다.
 * 이 직사각형을 세 개의 직사각형으로 나눈다.
 * 직사각형의 내부에는 1x1 크기마다 숫자가 쓰여있다.
 * 세 개의 직사각형의 내부의 숫자의 합을 구한 후
 * 모두 곱했을 때의 최댓값을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 최댓값을 구하려면 무조건 직사각형을 다 채워야 한다.
	 * 직사각형을 세 개의 직사각형으로 나누는 방법에는 6가지가 있다.
	 * 모든 경우를 다 구해보면 정답을 구할 수 있음
	 * 
	 * 내부의 합은 int인데 곱한 결과가 long일 수 있어서 전부 long으로 바꿈 (이거때문에 30분 고민 ㅠ)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long[][] map = new long[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			for (int j = 1; j <= m; j++)
				map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + (input.charAt(j - 1) - '0');
		}
		long answer = 0;
		for (int i = 1; i < n; i++)
			for (int j = i + 1; j < n; j++)
				answer = max(answer, map[i][m] * (map[j][m] - map[i][m]) * (map[n][m] - map[j][m]));
		for (int i = 1; i < m; i++)
			for (int j = i + 1; j < m; j++)
				answer = max(answer, map[n][i] * (map[n][j] - map[n][i]) * (map[n][m] - map[n][j]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[i][j] * (map[n][j] - map[i][j]) * (map[n][m] - map[n][j]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[n][j] * (map[i][m] - map[i][j]) * (map[n][m] - map[i][m] - map[n][j] + map[i][j]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[i][j] * (map[i][m] - map[i][j]) * (map[n][m] - map[i][m]));
		for (int i = 1; i < n; i++)
			for (int j = 1; j < m; j++)
				answer = max(answer, map[i][m] * (map[n][j] - map[i][j]) * (map[n][m] - map[n][j] - map[i][m] + map[i][j]));
		System.out.println(answer);
	}

	public static long max(long a, long b) {
		return a > b ? a : b;
	}
}