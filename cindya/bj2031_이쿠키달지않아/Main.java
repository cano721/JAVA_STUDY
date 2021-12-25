package cindya.bj2031_이쿠키달지않아;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken()),
                d = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        if(k > n) k = n; // 차를 마실 수 있는 횟수가 음식의 수보다 많으면 음식의 수로 변경
        int[][] dp = new int[k + 1][n + 1];
        int[] cont = new int[n + 1];

        // i번 음식을 먹을 떄가 마지막 영향이라고 했을 때 영향을 받는 음식의 수
        for(int i = 0; i < n; i++)
            cont[i + 1] = i - getContinuation(i, d);

        for(int i = 1; i <= k; i++){
            for(int j = 1; j <= n; j++){
                // i번째 잔을 이전 음식과 함께 먹은 경우와
                // (i - 1)번째 잔을 이번 음식에 영향이 오지 않는 시간에 마셨을 때 + i번째까지 영향이 오도록 마셨을 경우
                // 두 경우 중 더 많은 음식에 영향을 준 쪽을 선택
                dp[i][j] = Math.max(dp[i][j - 1], cont[j] + dp[i - 1][j - cont[j]]);
            }
        }

        System.out.println(dp[k][n]);
        br.close();
    }

    // 차 한 잔을 마신 후 idx가 마지막 영향을 받는 음식이라면 몇번째 음식을 먹을 때 차를 마셨는지 반환하는 함수
    private static int getContinuation(int idx, int d){
        int low = 0;
        int high = idx;
        while (low <= high){
            int mid = (low + high) / 2;
            if(a[idx] - a[mid] < d) // mid 음식을 먹을 때 영향을 받고 있었다면
                high = mid - 1; // high를 낮춤
            else // 영향을 받지 않았다면
                low = mid + 1; // low를 올림
        }
        return high;
    }
}