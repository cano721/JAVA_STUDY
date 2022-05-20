package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] sticker = { 14, 6, 5, 11, 3, 9, 2, 10 };
		System.out.println(solution.solution(sticker));
	}
}

class Solution {
	/**
	 * N개의 스티커가 원형으로 연결되어 있다.
	 * i번째 스티커를 때면 i-1번째와 i+1번째 스티커를 사용하지 못한다.
	 * 각 스티커에 숫자가 쓰여있을 때, 스티커를 때어 얻을 수 있는 최대 점수를 구하는 문제
	 * 
	 * 1번째 스티커를 무조건 때고 계산하는 dp
	 * n번째는 무조건 뗄 수 없으므로 n-1번째 스티커까지의 계산을 max로 활용
	 * 1번째 스티커를 안때고 2번째 스티커를 때고 계산하는 dp
	 * n번째 스티커를 뗄 수 있으므로 n번째 스티커까지의 계산을 max로 활용
	 * @param sticker 각 스티커에 쓰여있는 숫자
	 * @return 스티커를 땠을 때 얻을 수 있는 최대 점수
	 */
	public int solution(int sticker[]) {
		int n = sticker.length;
		if (n == 1) {
			return sticker[0];
		}
		int[][][] dp = new int[2][n][2];
		dp[0][0][0] = sticker[0];
		dp[0][1][0] = sticker[1];
		dp[0][1][1] = sticker[0];
		dp[1][1][0] = sticker[1];
		for (int i = 2; i < n; i++) {
			dp[0][i][0] = Math.max(dp[0][i - 2][0], dp[0][i - 1][1]) + sticker[i];
			dp[0][i][1] = Math.max(dp[0][i - 1][0], dp[0][i - 1][1]);
			dp[1][i][0] = Math.max(dp[1][i - 2][0], dp[1][i - 1][1]) + sticker[i];
			dp[1][i][1] = Math.max(dp[1][i - 1][0], dp[1][i - 1][1]);
		}
		return Math.max(Math.max(dp[1][n - 1][0], dp[1][n - 1][1]), Math.max(dp[0][n - 2][0], dp[0][n - 2][1]));
	}
}