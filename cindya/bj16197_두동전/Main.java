package cindya.bj16197_두동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static String[][] board;
    private static int[] rowMove = {-1, 1, 0, 0}, colMove = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        board = new String[nm[0]][nm[1]];
        List<int[]> coins = new ArrayList<>();
        int answer;

        // board 초기화
        for(int i = 0; i < nm[0]; i++){
            board[i] = br.readLine().split("");
            // 동전 위치 coins에 저장
            for(int j = 0; j < nm[1]; j++){
                if(board[i][j].equals("o"))
                    coins.add(new int[]{i, j});
            }
        }
        br.close();

        // DFS함수 호출
        answer = moveCoins(coins.get(0), coins.get(1), 1);
        if(answer > 10) answer = -1; // 결과가 10 이상이면 -1로 변경
        System.out.println(answer);
    }

    private static int moveCoins(int[] coin1, int[] coin2, int cnt){
        int min = 11;
        // 횟수가 10번이 넘어가면 멈춤
        if(cnt > 10){
            return cnt;
        }
        // 동전을 사방으로 보내기
        for(int i = 0; i < 4; i++){
            int row1 = coin1[0] + rowMove[i], col1 = coin1[1] + colMove[i];
            int row2 = coin2[0] + rowMove[i], col2 = coin2[1] + colMove[i];

            // 두 동전이 모두 게임판 안에 있는 경우
            if(0 <= row1 && row1 < board.length && 0 <= col1 && col1 < board[row1].length
            && 0 <= row2 && row2 < board.length && 0 <= col2 && col2 < board[row2].length){
                // 두 둥전이 모두 벽으로 간다면 다음 루프 진행
                if(board[row1][col1].equals("#") && board[row2][col2].equals("#"))
                    continue;
                // 벽에 부딫힌 동전을 원래 위치로 돌려놓음
                if(board[row1][col1].equals("#")){
                    row1 = coin1[0];
                    col1 = coin1[1];
                }
                if(board[row2][col2].equals("#")){
                    row2 = coin2[0];
                    col2 = coin2[1];
                }
                // 두 동전의 위치가 같다면 다음 루프 진행
                if(row1 == row2 && col1 == col2) continue;

                // 동전 위치 변경
                board[coin1[0]][coin1[1]] = ".";
                board[row1][col1] = "o";
                board[coin2[0]][coin2[1]] = ".";
                board[row2][col2] = "o";
                // 이동 진행
                min = Math.min(min, moveCoins(new int[]{row1, col1}, new int[]{row2, col2}, cnt + 1));
                // 동전 위치 원복
                board[row1][col1] = ".";
                board[coin1[0]][coin1[1]] = "o";
                board[row2][col2] = ".";
                board[coin2[0]][coin2[1]] = "o";
            }
            // 한 동전만 떨어진 경우, 최소 이동 횟수를 cnt로 바꾸고 루프를 멈춤
            else if((0 <= row1 && row1 < board.length && 0 <= col1 && col1 < board[row1].length)
                 || (0 <= row2 && row2 < board.length && 0 <= col2 && col2 < board[row2].length)){
                min = cnt;
                break;
            }
        }
        return min;
    }
}
