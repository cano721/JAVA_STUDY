package cindya.bj1806_부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), s = Integer.parseInt(st.nextToken());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0, end = 0, sum = numbers[0], min = Integer.MAX_VALUE;

        while (start < n){
            // 합이 s보다 작으면
            if(sum < s){
                // end가 마지막 인덱스면 break
                if(end == n - 1) break;
                // 아니면 end를 증가시키고 그 자리의 수를 sum에 더함
                end++;
                sum += numbers[end];
            }
            // 합이 s보다 크거나 같으면
            else{
                min = Math.min(min, end - start + 1); // min과 현재 start ~ end의 길이 중 더 짧은 것을 선택
                if(start == n - 1) break; // start가 마지막 인덱스면 break
                sum -= numbers[start]; // sum에서 start 자리의 수만큼을 빼고
                start++; // start 증가
                // start가 end보다 커졌을 경우
                if(start > end) {
                    // end도 증가시키고 sum에 인덱스의 값을 더함
                    end++;
                    sum += numbers[end];
                }
            }
        }
        // s 이상의 수를 만들 수 없는 경우, 0 출력
        if(min == Integer.MAX_VALUE) min = 0;

        System.out.println(min);
        br.close();
    }
}
