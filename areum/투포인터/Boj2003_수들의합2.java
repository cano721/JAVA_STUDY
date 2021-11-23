package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        int ans = 0; // 경우의 수
        int sum = 0; // 구간합
        int start = 0;
        int end = 0;

        while(true) {

            if(sum >= M) { // 구간합이 M보다 크거나 같으면,
                sum -= arr[start++]; // sum 에서 넣었던 수 빼주고, start += 1
            } else if(end == N) {
                break;
            } else if(sum < M) { // 구간합이 M보다 작으면,
                sum += arr[end++]; // sum 에 end 위치 값 넣어주고, end += 1
            }

            if(sum == M) { // 구간합이 M과 같으면
                ans++; // 경우 수 + 1
            }
        }
         System.out.println(ans);
    }
}
