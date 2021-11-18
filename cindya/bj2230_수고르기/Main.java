package cindya.bj2230_수고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        int min = Integer.MAX_VALUE;

        // a 초기화
        for(int i = 0; i < n; i++)
            a[i] = Integer.parseInt(br.readLine());

        Arrays.sort(a); // a 정렬

        for(int i = 0; i < n - 1; i++){
            int j = i + 1;
            for(; j < n && a[j] - a[i] < m; j++); // 두 수의 차가 m보다 크거나 같을 때까지 루프
            if(j == n) break; // i를 빼서 m보다 큰 차를 만들 수 있는 수가 없는 것이므로 루프 종료
            min = Math.min(min, a[j] - a[i]); // 더 작은 차를 선택
        }

        System.out.println(min);

        br.close();
    }
}
