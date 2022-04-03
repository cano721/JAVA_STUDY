package day220402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7579_앱 {

    static int N, M;
    static int[] appMemory, cancleCost;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        appMemory = new int[N + 1];
        cancleCost = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            appMemory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cancleCost[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i <= N; i++) {
            sum += cancleCost[i];
        }

        //dp[i][j] : i번째 앱까지 고려했을 때, j만큼의 비용으로 얻을 수 있는 최대 메모리.
        //j의 최대값은 5개의 앱을 모두 비활성화 할때의 비용의 합이다.
        dp = new int[N + 1][sum + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {
                if (cancleCost[i] <= j)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cancleCost[i]] + appMemory[i]);
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
            }
        }

        //dp[N][i]의값은 i만큼의 비활성화 비용을 사용할 때 최대의 메모리값이므로
        //문제에서 요구하는 M만큼의 메모리를 넘는 순간의 i값이 최소 비활성화 비용이라고 볼수있따.
        for (int i = 0; i <= sum; i++) {
            if (dp[N][i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
