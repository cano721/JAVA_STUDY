package 투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 전체 용액의 수
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = N-1;
        int diff = Integer.MAX_VALUE; // 두 수의 차이 저장
        int ans1 = 0;
        int ans2 = 0;
        while(start < end) {

            // 지금의 두 수 차이가 0에 더 가까우면
            if(Math.abs(arr[start] + arr[end]) < diff) {
                diff = Math.abs(arr[start] + arr[end]); // 값 변경
                ans1 = arr[start];
                ans2 = arr[end];
            }

            // 두 수의 차이가 0보다 크면
            if(arr[start] + arr[end] > 0) {
                end--; // end 를 더 작은 값을 가진 쪽으로 이동
            } else { // 0보다 작으면
                start++; // 더 큰 값을 가진 쪽으로 이동
            }
        }

        System.out.println(ans1+ " " + ans2);
    }
}
