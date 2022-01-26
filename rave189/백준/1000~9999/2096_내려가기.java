package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3개씩 N개의 줄로 이루어진 판이 있다.
 * 각 칸에는 0~9까지의 숫자가 적혀있다.
 * 내려가기 놀이를 하려는데 다음 줄로 내려갈 때에는 다음과 같은 제약조건이 있다.
 * 1. 바로 아래의 수로 내려간다.
 * 2. 바로 아래의 수와 인접한 수로 내려간다.
 * 따라서 맨 왼쪽은 바로 아래 수와 아래 수와 인접한 오른쪽 수로 내려갈 수 있고,
 * 맨 오른쪽은 바로 아래 수와 아래 수와 인접한 왼쪽 수로 내려갈 수 있고, 
 * 가운데는 어느쪽이든 내려갈 수 있다.
 * 이 때, 얻을 수 있는 최대 점수와 최소 점수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 최대만 저장하는 dp와 최소만 저장하는 dp를 만들어 내려가는 수가 최대 최소이도록 dp를 수행한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[0].length; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] maxDp = new int[n + 1][3];
		int[][] minDp = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + arr[i][0];
			maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2])) + arr[i][1];
			maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + arr[i][2];
			minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + arr[i][0];
			minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2])) + arr[i][1];
			minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + arr[i][2];
		}
		int max = Math.max(maxDp[n][0], Math.max(maxDp[n][1], maxDp[n][2]));
		int min = Math.min(minDp[n][0], Math.min(minDp[n][1], minDp[n][2]));
		System.out.println(String.format("%d %d", max, min));
	}
}