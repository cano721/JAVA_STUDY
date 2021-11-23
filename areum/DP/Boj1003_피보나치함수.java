package DP;
import java.io.*;

public class Boj1003_피보나치함수 {
    static int [][] dp = new int[41][2]; // [N<=40][0과 1 호출 횟수]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        // 호출횟수 초기 설정
        dp[0][0] = 1;	// N=0 → 0 호출 횟수
        dp[0][1] = 0;	// N=0 → 1 호출 횟수
        dp[1][0] = 0;	// N=1 → 0 호출 횟수
        dp[1][1] = 1;	// N=1 → 1 호출 횟수

        for(int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            fibo(N);
            bw.write(dp[N][0] + " " + dp[N][1] + "\n");
        }

        // 출력 후 종료
        bw.flush();
        bw.close();
    }

    static int[] fibo(int n) {
        if(n>1) {
            // n에 대한 0, 1의 호출 횟수가 0일 때
            if(dp[n][0] == 0 || dp[n][1] == 0) {
                // 각 n에 대한 0, 1 호출 횟수를 재귀 호출
                // N=2, fibo(2) = fibo(1) + fibo(0) → 0:1 / 1:1
                // N=3, fibo(3) = (fibo(1) + fibo(0)) + fibo(1) → 0:1 / 1:2
                dp[n][0] = fibo(n - 1)[0] + fibo(n - 2)[0];
                dp[n][1] = fibo(n - 1)[1] + fibo(n - 2)[1];
            }
        }
        return dp[n];
    }
}
