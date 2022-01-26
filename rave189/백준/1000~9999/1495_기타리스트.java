package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 곡을 공연에서 연주하려고 한다.
 * 이 공연에서 볼륨을 0 이상 M이하로 설정할 수 있다.
 * 공연에서 곡이 시작하기 전 볼륨을 키우거나 줄일 수 있다.
 * 볼륨의 조정은 V + arr[i] 또는 V - arr[i]로만 조정할 수 있다.
 * 이 때 마지막 곡의 볼륨의 최댓값을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 2차원 dp를 사용하여 i번째 곡에서 나올 수 있는 볼륨의 가짓수를 i+1에 저장한다.
	 * 
	 * 1차원 dp를 사용해서 풀어보려고 함.
	 * 근데 1차원 dp로는 5에서 +1, -1을 통해 4와 6을 업데이트해주었을 때, 6도 볼륨을 조정할 수 있는 수인 경우를
	 * 들어가지 못한다. (+가 문제)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] music = new int[n];
		for (int i = 0; i < n; i++)
			music[i] = Integer.parseInt(st.nextToken());
		int[][] dp = new int[n + 1][m + 1];
		dp[0][s]++;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				if (dp[i][j] == 0)
					continue;
				if (j + music[i] <= m)
					dp[i + 1][j + music[i]] = 1;
				if (j - music[i] >= 0)
					dp[i + 1][j - music[i]] = 1;
			}
		}
		for (int i = m; i >= 0; i--) {
			if (dp[n][i] != 0) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
}