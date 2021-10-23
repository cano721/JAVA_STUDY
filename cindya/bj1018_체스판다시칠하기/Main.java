package cindya.bj1018_체스판다시칠하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public enum Color{W, B}
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Color[] std = {Color.W, Color.B, Color.W, Color.B, Color.W, Color.B, Color.W, Color.B}; // 보드를 확인할 떄 행의 시작으로 기준삼을 색 배열
        String[] xy = br.readLine().split(" ");
        int x = Integer.parseInt(xy[0]), y = Integer.parseInt(xy[1]);
        String[][] board = new String[x][]; // 보드
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < x; i++){
            board[i] = br.readLine().split("");
        }

        for(int i = 0; i <= x - 8; i++){ // 시작 행 인덱스 i
            for(int j = 0; j <= y - 8; j++){ //시작 열 인덱스 j
                int cnt = 0; // 칠해야하는 부분 count
                for(int k = i; k < i + 8; k++){ // i부터 8개 행 루프
                    int n = std[k - i].ordinal(); // 현재 행의 시작 기준 색 번호
                    for(int l = j; l < j + 8; l++){ // j부터 8개 열 루프
                        if(!Color.values()[n].toString().equals(board[k][l])) cnt++; // 기준 색과 보드 색이 다르면 count 증가
                        n = (n + 1) % 2; // 기준 색 반전
                    }
                }
                cnt = Math.min(cnt, (8 * 8) - cnt); // 기준 색 반대의 경우에 칠할 칸이 더 작으면 반대로 변경
                min = Math.min(cnt, min); // 이전 경우보다 작으면 값 변경
            }
        }

        System.out.println(min);

        br.close();
    }
}
