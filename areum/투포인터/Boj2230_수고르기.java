package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2230_수고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int n=0; n<N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int start = 0;
        int end = 0;
        int ans = Integer.MAX_VALUE;
        while(start < N) {
            if (arr[start] - arr[end] < M) {
                start++;
                continue;
            }

            if (arr[start] - arr[end] == M) {
                ans = M;
                break;
            }

            // 최소 차이로 갱신
            ans = Math.min(ans, arr[start] - arr[end]);
            end++;

        }

        System.out.println(ans);
    }
}
