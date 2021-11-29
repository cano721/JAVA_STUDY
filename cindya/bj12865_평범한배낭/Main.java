package cindya.bj12865_평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int WEIGHT = 0, VALUE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), k = Integer.parseInt(st.nextToken());
        int[][] carry = new int[n + 1][k + 1], wv = new int[n + 1][];

        for(int i = 1; i <= n; i++)
            wv[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 1; i <= n; i++){ // 물건을 행 헤더로 간주
            for(int j = 1; j <= k; j++){ // 가방 무게를 열 헤더로 간주
                carry[i][j] = carry[i - 1][j]; // 가방 무게가 j일 때 최대 물건 가치 = 이전 물건까지 포함하여 무게가 j일 때 최대 가치
                if(wv[i][0] <= j) // 이번 물건의 가치가 가방 무게보다 같거나 작으면
                    // 기존 가치와 이번 물건을 포함해 계산한 가치 중 큰 것을 선택
                    carry[i][j] = Math.max(carry[i][j], wv[i][1] + carry[i - 1][j - wv[i][0]]);
            }
        }

        System.out.println(carry[n][k]);

        br.close();
    }
}