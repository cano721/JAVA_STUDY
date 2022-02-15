package programmers;

import java.util.Arrays;

public class d220215_합승택시요금 {

	public static void main(String[] args) {
		int n = 6; 
		int s = 4; 
		int a = 6; 
		int b = 2; 
		int[][] fares ={{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		System.out.println(solution(n, s, a, b, fares));
	}
	/*플로이드 워셜*/
	static final int MAX = 20000001;
	private static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }

        for(int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];

            dp[start][end] = cost;
            dp[end][start] = cost;
        }

        for(int k = 1; k < n+1; k++) {
            for(int i = 1; i < n+1; i++) {
                for(int j = 1; j < n+1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }


        int answer = dp[s][a] + dp[s][b];
        // 경유지점을 끼는경우
        // s -> t t -> a + t -> b
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] +dp[i][b]);
        }
        return answer;
    }

}
