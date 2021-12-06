package cindya.bj2096_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][], maxScores = new int[n][3], minScores = new int[n][3];

        for(int i = 0; i < n; i++)
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();

        maxScores[0] = minScores[0] = Arrays.copyOf(board[0], 3); // 두 배열의 첫째줄을 보드의 첫째줄로 세팅
        for(int i = 1; i < n; i++){
            // 선택 가능한 이전 결과 중 큰 값을 선택한 후 n번째 값을 더함
            maxScores[i][0] = board[i][0] + Math.max(maxScores[i - 1][0], maxScores[i - 1][1]);
            maxScores[i][1] = board[i][1] + Math.max(maxScores[i - 1][1], Math.max(maxScores[i - 1][0], maxScores[i - 1][2]));
            maxScores[i][2] = board[i][2] + Math.max(maxScores[i - 1][1], maxScores[i - 1][2]);

            // 선택 가능한 이전 결과 중 작은 값을 선택한 후 n번째 값을 더함
            minScores[i][0] = board[i][0] + Math.min(minScores[i - 1][0], minScores[i - 1][1]);
            minScores[i][1] = board[i][1] + Math.min(minScores[i - 1][1], Math.min(minScores[i - 1][0], minScores[i - 1][2]));
            minScores[i][2] = board[i][2] + Math.min(minScores[i - 1][1], minScores[i - 1][2]);
        }
        System.out.println(Arrays.stream(maxScores[n - 1]).max().getAsInt() + " " + Arrays.stream(minScores[n - 1]).min().getAsInt());
    }
}
