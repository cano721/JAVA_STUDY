package cindya.bj2559_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        int[] temperatures = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0, end = k, sum = 0, max;

        // 0 ~ k까지의 합
        for(int i = 0; i < k; i++)
            sum += temperatures[i];
        max = sum;

        // end가 n이 될 때까지
        while (end < n){
            sum -= temperatures[start++]; // 앞부분을 빼고 start 증가
            sum += temperatures[end++]; // 뒷부분을 더하고 end 증가
            max = Math.max(max, sum); // max와 sum 중 더 큰 값을 선택
        }

        System.out.println(max);
        br.close();
    }
}
