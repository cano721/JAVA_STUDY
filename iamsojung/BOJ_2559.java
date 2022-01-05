package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for (int i = 0; i < K; i++) {
            max = num[i] + max;
        }
        int sum = max;
        for (int j = K; j < N; j++) {
            int temp = sum + num[j] - num[j - K];
            max = Math.max(temp, max);
            sum = temp;
        }
        System.out.println(max);

    }
}
