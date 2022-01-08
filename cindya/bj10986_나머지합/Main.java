package cindya.bj10986_나머지합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] count = new int[m];
        int sum = 0;
        long answer;

        // 구해야하는 결과 : (a[i] - a[j]) % m = 0 => (a[i] % m) - (a[j] % m) = 0 => a[i] % m = a[j] % m
        for(int i = 0; i < n; i++) {
            sum = (sum + a[i]) % m;
            count[sum]++; // count에 나머지가 같은 누적합의 개수를 저장
        }

        answer = count[0]; // 나머지가 0인 경우 단독으로 조건을 만족하므로 개수를 더함
        for(int i = 0; i < m; i++)
            // 나머지가 i인 누적합끼리는 a[i] % m = a[j] % m을 만족하므로 count[i]를 순서없이 2개 뽑는 경우의 수를 더함
            answer += (long)count[i] * (count[i] - 1) / 2;

        System.out.println(answer);
        br.close();
    }
}
