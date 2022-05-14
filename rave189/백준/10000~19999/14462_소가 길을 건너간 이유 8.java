package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * N종류의 소가 있다.
 * 농장에는 왼쪽 목초지와 오른쪽 목초지가 있다.
 * 왼쪽 목초지에는 N종류의 소가 있고, 오른쪽 목초지도 N종류의 소가 있다.
 * 각 종류의 소는 해당 목초지에서 한 목초지씩 차지하고 있다.
 * 왼쪽 목초지의 i번 종과 오른쪽 목초지의 j번 종이 |i-j| <= 4라면 친한 사이이다.
 * 왼쪽 목초지와 오른쪽 목초지의 소를 이어줄 횡단보도를 설치하려고 한다.
 * 설치할 수 있는 최대 개수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * i번째 종과 j번째 종 사이에 횡단보도를 설치할 때 최대 개수를 dp로 설정한다.
	 * 서로 안친한 사이라면 dp[i-1][j]와 dp[i][j-1]중에 큰 값을 고른다.
	 * 서로 친한 사이라면 dp[i][j]와 dp[i-1][j-1]중에 큰 값을 고른다.
	 * 
	 * 근데 왜맞음?
	 * 앱 생각하면서 대충 때려봤는데 그냥 나오네
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] left = new int[n + 1];
		int[] right = new int[n + 1];
		for (int i = 1; i <= n; i++)
			left[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++)
			right[i] = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (Math.abs(left[i] - right[j]) > 4)
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				else
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
			}
		}
		System.out.println(dp[n][n]);
	}
}