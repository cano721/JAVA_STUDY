package cindya.bj2579_계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] steps = new int[n];
        int[][] records = new int[n][3];

        for(int i = 0; i < n; i++)
            steps[i] = Integer.parseInt(br.readLine());
        br.close();

        records[0][1] = steps[0]; // 첫번째 계단을 밟은 경우를 첫번째 계단 값으로 세팅

        for(int i = 1; i < n; i++){
            // i번째 계단을 안밟은 경우에 이전에 밟은 계단이 한 개인 경우와 두개인 경우 중 큰 경우를 택함
            records[i][0] = Math.max(records[i - 1][1], records[i - 1][2]);
            records[i][1] = steps[i] + records[i - 1][0]; // 이전 계단을 안밟은 경우에 i번째 계단 값을 더함
            records[i][2] = steps[i] + records[i - 1][1]; // 이전 계단을 한 번 밟은 경우에 i번째 계단 값을 더함
        }

        System.out.println(Math.max(records[n - 1][1], records[n - 1][2]));
    }
}
