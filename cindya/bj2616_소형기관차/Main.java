package cindya.bj2616_소형기관차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), m;
        int[] passenger = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sum = new int[n + 1];
        m = Integer.parseInt(br.readLine());
        int[][] res = new int[n + 1][4];

        // 누적합 저장
        for(int i = 0; i < n; i++)
            sum[i + 1] += sum[i] + passenger[i];

        for(int i = 1; i < 4; i++){
            for(int j = m; j <= n; j++){
                // i번째 기관차가 끌 열차를 선택할 때, 이전 열차까지의 i번째 기관차가 끄는 경우와
                // m번 전 열차까지 i - 1번 기관차가 끌고, i번째 기관차가 j번째 열차까지 끄는 경우 중 큰 값을 선택
                res[j][i] = Math.max(res[j - 1][i], res[j - m][i - 1] + (sum[j] - sum[j - m]));
            }
        }
        System.out.println(res[n][3]);
        br.close();
    }
}
