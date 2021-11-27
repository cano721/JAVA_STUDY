package cindya.bj1535_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] health = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] happy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] maxHappy = new int[n + 1][100];

        br.close();

        for (int i = 1; i <= n; i++){
            for (int j = 1; j < 100; j++){
                maxHappy[i][j] = maxHappy[i - 1][j]; // 이전 사람까지 인사했을 때 체력을 j 이하로 쓰면서 받은 최대의 기쁨
                if(health[i - 1] <= j) // 이번 사람과 인사할 때 쓰이는 체력이 j 이하이면
                    // 이전 사람까지 체력 j 당 최대 기쁨 vs 이번 사람과 인사할 때의 기쁨 + 남은 체력으로 얻을 수 있는 최대 기쁨
                    maxHappy[i][j] = Math.max(maxHappy[i][j], happy[i - 1] + maxHappy[i - 1][j - health[i - 1]]);
            }
        }
        System.out.println(maxHappy[n][99]); // 체력을 99까지 썼을 때 얻을 수 있는 최대 기쁨
    }
}
