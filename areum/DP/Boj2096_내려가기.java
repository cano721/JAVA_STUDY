package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2096_내려가기 {
    static int N;
    static int arr[][];
    static int max[][], min[][];
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][4];
        max = new int[N + 1][4];
        min = new int[N + 1][4];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            max[i][1] += Math.max(max[i - 1][1], max[i - 1][2]) + arr[i][1];
            max[i][2] += Math.max(Math.max(max[i - 1][1], max[i - 1][2]), max[i - 1][3]) + arr[i][2];
            max[i][3] += Math.max(max[i - 1][2], max[i - 1][3]) + arr[i][3];

            min[i][1] += Math.min(min[i - 1][1], min[i - 1][2]) + arr[i][1];
            min[i][2] += Math.min(Math.min(min[i - 1][1], min[i - 1][2]), min[i - 1][3]) + arr[i][2];
            min[i][3] += Math.min(min[i - 1][2], min[i - 1][3]) + arr[i][3];
        }

        int MAX = 0, MIN = Integer.MAX_VALUE;
        for (int i = 1; i <= 3; i++) {
            MAX = Math.max(MAX, max[N][i]);
            MIN = Math.min(MIN, min[N][i]);
        }

        System.out.println(MAX + " " + MIN);
    }
}
