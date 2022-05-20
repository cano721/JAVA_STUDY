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
	 * N���� ��ƼĿ�� �������� ����Ǿ� �ִ�.
	 * i��° ��ƼĿ�� ���� i-1��°�� i+1��° ��ƼĿ�� ������� ���Ѵ�.
	 * �� ��ƼĿ�� ���ڰ� �������� ��, ��ƼĿ�� ���� ���� �� �ִ� �ִ� ������ ���ϴ� ����
	 * 
	 * 1��° ��ƼĿ�� ������ ���� ����ϴ� dp
	 * n��°�� ������ �� �� �����Ƿ� n-1��° ��ƼĿ������ ����� max�� Ȱ��
	 * 1��° ��ƼĿ�� �ȶ��� 2��° ��ƼĿ�� ���� ����ϴ� dp
	 * n��° ��ƼĿ�� �� �� �����Ƿ� n��° ��ƼĿ������ ����� max�� Ȱ��
	 * @param sticker �� ��ƼĿ�� �����ִ� ����
	 * @return ��ƼĿ�� ���� �� ���� �� �ִ� �ִ� ����
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