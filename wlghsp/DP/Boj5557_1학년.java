package baekjoon;

/*
상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다.
상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다.
예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.

상근이는 올바른 등식을 만들려고 한다. 상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다.
따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다. 예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에, 상근이가 만들 수 없는 등식이다.
숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100) 둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.

출력
첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다. 이 값은 263-1 이하이다.

11
8 3 2 4 8 7 2 4 0 8 8

10

40
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0 0 1 1

7069052760


 */
// https://steady-coding.tistory.com/171 블로그 보면서 공부했습니다...ㅡㅡ

import java.io.*;
import java.util.StringTokenizer;

public class Boj5557_1학년 {
    static int N;
    static int[] operand;
    static long[][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        operand = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            operand[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[21][100];

        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j < 100; j++) {
                dp[i][j] = -1;
            }
        }

        bw.write(recursion(operand[0], 0) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static long recursion(int sum, int idx) {
        // sum의 범위는 0부터 20까지.
        if (sum < 0 || sum > 20) {
            return 0;
        }

        // operand[0] ~ operand[N - 2] 까지 연산을 완료했으면
        // 여태까지의 값과 operand[N - 1]을 비교해야 함.
        if (idx == N - 2) {
            return (sum == operand[N - 1]) ? 1 : 0;
        }

        if (dp[sum][idx] != -1) {
            return dp[sum][idx];
        }

        dp[sum][idx] = 0;

        return dp[sum][idx] += recursion(sum + operand[idx + 1], idx + 1) + recursion(sum - operand[idx + 1], idx + 1);
    }

}