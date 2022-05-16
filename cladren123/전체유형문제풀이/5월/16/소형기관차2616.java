package studyGroup.may.may16;

import java.util.*;
import java.io.*;


/*

소형기관차 3대로 최대로 운송할 수 있는 손님 수를 출력

https://velog.io/@leeinae/Algorithm-%EB%B0%B1%EC%A4%80-2616-%EC%86%8C%ED%98%95%EA%B8%B0%EA%B4%80%EC%B0%A8-java
DP


 */

public class 소형기관차2616 {

    static int n; // 객차의 수 5만 이하
    static int[] number; // 손님의 수 100명 이하
    static int m; // 소형 기관차가 최대로 끌 수 있는 객차의 수, n의 1/3보다 작다.

    static int[] numberSum; // 1번 ~ n번 까지의 손님 수 합 저장
    static int[][] dp; // 소형기관차 i대가 객차 j대를 끌었을 때의 손님의 수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        number = new int[n+1];
        numberSum = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++)
        {
            number[i] = Integer.parseInt(st.nextToken());
            numberSum[i] = numberSum[i-1] + number[i];
        }
        m = Integer.parseInt(br.readLine());
        dp = new int[4][n+1];

        for(int i = 1; i < 4; i++)
        {
            for(int j = i * m; j <= n; j++)
            {
                // 건너 뛰거나
                // m 전에 최댓값 + j~m 까지의 합계를 비교
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - m] + (numberSum[j] - numberSum[j - m]));
            }
        }

        System.out.println(dp[3][n]);

    }

}
