package DP;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj2565_전깃줄 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N]; // // 해당 위치까지의 가장 많이 설치할 수 있는 전깃줄 개수
        int[][] line = new int[N][2];// 0: A 전깃줄 번호 | 1: B 전깃줄 번호
        int max = 0;

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }

        // A 전봇대 기준으로 오름차순
        Arrays.sort(line, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // Bottom-Up
        for (int i = 0; i<N; i++) {
            // 최소 개수 1로 초기화
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                // 과거 A의 전깃줄과 이어진 B값 보다 현재 B값이 더 커야함
                if (line[i][1] > line[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]); // 최댓값 갱신
        }

        // 제거 해야할 전깃줄 최소 개수 = 전체 - 가장 많이 설치할 수 있는 개수
        System.out.println(N - max);
    }
}

