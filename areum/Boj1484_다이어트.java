import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Boj1484_다이어트 {
    // X^2 - Y^2 = G 가 되는 X를 구하는 문제
    // G <= 100,000 이므로, X값의 상한은 약 50,000
    // (X^2 - (X-1)^2 = 100000 일 때가 최대 X값
    static final int MAX = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());

        // 초기 제곱값 선언
        long[] arr =  new long[MAX + 1];
        for(int i = 1; i <= MAX; i++) {
            arr[i] = i * i;
        }

        // 몸무게는 0kg가 나올 수 없으므로 start 0이 아니라, 1부터 출발
        int start = 1;
        int end = 2;
        List<Integer> ans = new ArrayList<Integer>();

        while(end <= MAX) {
            long num = arr[end] - arr[start];
            if(num == G) {
                ans.add(end);
            }
            // G보다 크다면 start 를 증가시켜 num 감소
            if(num > G) {
                start++;
            }
            // G보다 작다면 end 값을 증가시텨 num 증가
            if(num <= G) {
                end++;
            }
        }


        // 오름차순 정렬
        Collections.sort(ans);
        // 찾을 수 없거나, G가 1인 경우(전에 몸무가게 0일수가 없으므로) -1 반환
        if(ans.isEmpty() || G == 1) {
            System.out.println(-1);
        } else {
            for(int g : ans) {
                System.out.println(g);
            }
        }


    }
}
