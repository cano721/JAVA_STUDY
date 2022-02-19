package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
    int mod = 1000000007;
    /**
     * 2xN Ÿ�� ����
     * @param n Ÿ���� ���� ����
     * @return Ÿ���� ��ġ�ϴ� ����� ��
     */
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++)
            dp[i] = (dp[i-1] + dp[i-2])%mod;
        return dp[n];
    }
}