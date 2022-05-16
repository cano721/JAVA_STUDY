package studyGroup.may.may17;

import java.util.*;
import java.io.*;

/*

3개의 직사각형으로 나눌 수 있는 경우의 수는 6가지

가로3개
세로3개
ㅏ
ㅓ
ㅗ
ㅜ

누적합을 활용 나누어진 직사각형의 합을 구한다.
6가지 모양의 경우의 수를 종합해 가장 큰 값을 출력

수의 범위가 int를 넘기 때문에 long으로 바꿔준다.

 */

public class 직사각형으로나누기1451 {

    static int n,m; // 세로, 가로
    static int[][] board; // 주어진 숫자
    static int[][] prefix; // 누적합
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        prefix = new int[n+1][m+1];

        for (int i = 1; i < n+1; i++) {
            String one = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j+1] = one.charAt(j) - '0';
                prefix[i][j+1] = board[i][j+1] + prefix[i][j] + prefix[i-1][j+1] - prefix[i-1][j];
            }
        }

        answer = 0;

        case1();
        case2();
        case3();

        System.out.println(answer);

    }

    // 가로3개 비교
    public static void case1()
    {
        // 3개의 구역을 나누 2개의 점 구하기
        for(int i = 1; i < n-1; i++)
        {
            for(int j = i+1; j < n; j++)
            {
                int k = n - j;

                long one = prefix[i][m];
                long two = prefix[j][m] - one;
                long three = prefix[n][m] - one - two;
                long result = one * two * three;

                answer = Math.max(answer, result);
            }
        }
    }

    // 세로3개 비교
    public static void case2()
    {
        // 3개의 구역을 나누 2개의 점 구하기
        for(int i = 1; i < m-1; i++)
        {
            for(int j = i+1; j < m; j++)
            {
                int k = m - j;

                long one = prefix[n][i];
                long two = prefix[n][j] - one;
                long three = prefix[n][m] - one - two;
                long result = one * two * three;

                answer = Math.max(answer, result);
            }
        }
    }

    // ㅗ, ㅏ, ㅜ, ㅓ
    public static void case3()
    {
        for(int i = 1; i < n; i++)
        {
            for(int j = 1; j < m; j++)
            {
                long sq1 = prefix[i][j];
                long sq2 = prefix[i][m] - sq1;
                long sq3 = prefix[n][j] - sq1;
                long sq4 = prefix[n][m] - sq2 - sq3 - sq1;

                // ㅏ
                long result1 = (sq1 + sq3) * sq2 * sq4;

                // ㅜ
                long result2 = (sq1 + sq2) * sq3 * sq4;

                // ㅓ
                long result3 = (sq2 + sq4) * sq1 * sq3;

                // ㅗ
                long result4 = (sq3 + sq4) * sq1 * sq2;

                answer = Math.max(answer, result1);
                answer = Math.max(answer, result2);
                answer = Math.max(answer, result3);
                answer = Math.max(answer, result4);
            }
        }
    }
}
