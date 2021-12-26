import java.io.*;
import java.util.*;


public class Solv2031 {
    static int T, N, D, K;
    static int[] input, cal;
    static int[][] dp;

    public static int binarySearch(int idx) {
        int mid;
        int left = 0;
        int right = idx;
        while (left  + 1 < right) {
            mid = (left + right) / 2;
            if (input[mid] <= input[idx] - D) left = mid ;
            else right = mid;
        }
        return right;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        //input
        T = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        input = new int[N + 1];
        cal = new int[N + 1];
        dp = new int[K + 1][N + 1];


        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        for (int i = 1; i <= N; i++) {
            cal[i] = i - binarySearch(i) + 1;

        }

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], cal[j] + dp[i - 1][j - cal[j]]);
            }
        }

        System.out.println(dp[K][N]);
    }
}


