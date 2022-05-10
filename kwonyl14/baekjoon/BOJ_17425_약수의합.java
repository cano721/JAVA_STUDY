package day220510;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
1  2  3  4  5  6  7  8
8보다 작거나 같은 수의 약수
1 1
2 1 2
3 1 3
4 1 2 4
5 1 5
6 1 2 3 6
7 1 7
8 1 2 4 8
121의 약수를 구할때
11까지만 구해본다?
1
 */
public class BOJ_17425_약수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long dp[] = new long[1000001];
        dp[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            //백만이하의 수중 i의 배수들에게 해당 수를 더해준다.
            //2의 배수는 2, 4, 6, 8, 10 ... 따라서 해당 수들의 약수에 2가 포함된다.
            for (int j = 1; i*j <= 1000000; j++) {
                dp[i*j] += i;
            }
            dp[i] += (dp[i-1]+1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append("\n");
        }

        System.out.println(sb);
    }
}
