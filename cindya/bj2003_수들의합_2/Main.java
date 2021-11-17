package cindya.bj2003_수들의합_2;

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
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0, end = 0, sum = 0, cnt = 0;

        while (start <= end && (end < n || sum >= m)){
            if(sum < m){ // 합이 m보다 작으면 end 증가
                sum += numbers[end++];
            }
            else{ // 합이 m보다 크거나 같으면 start 증가
                sum -= numbers[start++];
            }
            if(sum == m) cnt++; // sum이 m과 같으면 카운트 증가
        }

        System.out.println(cnt);
        br.close();
    }
}
