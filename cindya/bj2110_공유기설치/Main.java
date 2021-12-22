package cindya.bj2110_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), c = Integer.parseInt(st.nextToken());
        int min = 1, max, answer = 0;
        int[] houses = new int[n];

        for(int i = 0; i < n; i++)
            houses[i] = Integer.parseInt(br.readLine());
        br.close();
        Arrays.sort(houses); // 집을 좌표 순으로 정렬
        max = houses[n - 1] - houses[0]; // 최대 거리를 첫번째 집과 마지막 집 사이의 거리로 초기화

        // 최소값이 최대값보다 크거나 같아지면 루프 중지
        while (min <= max){
            int mid = (max + min) / 2; // 중간 거리
            int front = houses[0], cnt = 1; // 1번 집을 기준으로 하고, 1번 집에 이미 공유기를 설치한 것으로 상정
            for(int i = 1; i < n; i++){
                // front와의 거라가 mid와 같거나 넘는 집이 있으면
                if(houses[i] - front >= mid){
                    cnt++; // 카운트 증가
                    front = houses[i]; // 이 집을 front로 설정
                }
            }
            if(c <= cnt){ // mid 거리를 만족하는 집의 수가 설치하려는 공유기 개수 이상이면
                answer = Math.max(answer, mid); // 기존 값과 현재 mid 중 큰 값을 택함
                min = mid + 1; // 최소값을 mid + 1로 설정
            }
            else // 만족하는 집의 수가 공유기 개수보다 적으면
                max = mid - 1; // 최대 값을 mid - 1로 설정
        }
        System.out.println(answer);
    }
}
