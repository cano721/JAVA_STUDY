package cindya.bj23635_산타로부터의선물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] gifts;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        int high;
        gifts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        high = Arrays.stream(gifts).sum() / k; // 전체 기쁨 수치의 평균을 high로 설정

        // 1 ~ high까지 i를 최솟값으로 설정하여 함수 호출
        for(int i = 1; i <= high; i++)
            getDiffSum(k, i);

        System.out.println(answer);
    }

    public static void getDiffSum(int k, int min){
        int diffSum = 0, sum = 0, cnt = 0;
        for(int gift : gifts){
            // sum이 최솟값보다 크면
            if(sum >= min){
                diffSum += sum - min; // diffSum에 sum과 min의 차를 더하고
                cnt++; // cnt 증가
                if(answer < diffSum) return; // diffSum이 answer보다 크면 리턴
                if(cnt == k){ // k명에게 줄 선물이 모두 정해졌으면
                    answer = diffSum; // answer을 diffSum으로 바꾸고 리턴
                    return;
                }
                // 아직 k명의 선물이 다 정해지지 않았다면
                sum = 0; // sum 초기화
            }
            sum += gift; // sum에 i번째 선물의 기쁨 수치를 더함
        }
        // 루프 후 마지막 sum 값을 확인하고 한 번 더 연산
        if(sum >= min){
            diffSum += sum - min;
            cnt++;
        }
        // k명의 선물이 준비 되었으면 answer과 diffSum 중 더 큰 값을 선택
        if(cnt == k) answer = Math.min(answer, diffSum);
    }
}
