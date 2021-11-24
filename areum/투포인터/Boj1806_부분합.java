package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열 길이
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int total = 0;
        int start = 0;
        int end = 0;
        while(start<=N && end<=N) { // 배열 범위 안에서 찾기
            if(total>=S && ans>end-start) // 가장 짧은 길이 찾기
                ans = end - start;

            if(total<S) { // total이 S보다 작으면
                total += arr[end++]; // total이 S보다 커질때까지 end++
            } else { // total이 S보다 커지면
                total -= arr[start++]; // total이 S보다 작을때까지 start++
            }
        }

        if(ans == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }
}
