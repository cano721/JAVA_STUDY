package cindya.bj16173_점프왕쩰리_small;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = "Hing";
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for(int i = 0; i < n; i ++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        boolean flag = isPossible(n, 0, 0);

        if(flag) answer = "HaruHaru"; // 결과가 true면 출력할 문자열 변경
        System.out.println(answer);
    }

    private static boolean isPossible(int n, int x, int y){
        boolean flag = false;

        if(x == n - 1 && y == n - 1) // 끝 점 도착
            return true;

        if(board[x][y] == 0) return false; // 이동 가능한 거리가 0인 경우 false 반환

        // 오른쪽 이동
        if(x + board[x][y] < n) { // 이동 지점이 게임판을 넘어가지 않는 경우
            flag = isPossible(n, x + board[x][y], y); // 이동
            if(flag) return flag; // 결과가 true면 반환
        }

        // 아래로 이동
        if(y + board[x][y] < n) // 이동 지점이 게임판을 넘어가지 않는 경우
            flag = isPossible(n, x, y + board[x][y]); // 이동

        return flag;
    }
}
