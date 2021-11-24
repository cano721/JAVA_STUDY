package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1018_체스판 {
    static int[][] board;
    static int min = 64; // 8*8

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if(str.charAt(j) == 'W') {
                    board[i][j] = 1; // W 일때는 1
                } else {
                    board[i][j] = 0; // B 일때는 0
                }
            }
        }

        for (int i = 0; i < N-7; i++) { // 8*8의 체스판이 가능할 때까지
            for (int j = 0; j < M-7; j++) {
                find(i, j);
            }
        }

        System.out.println(min);
    }

    static void find(int x, int y) {
        int cnt = 0;
        int start = board[x][y]; // 첫 번째 칸의 색

        for(int i=x; i<x+8; i++) {
            for(int j=y; j<y+8; j++) {
                if(board[i][j] != start)
                    cnt++; // 색칠해야 할 경우 cnt++

                // 열이 변경될 때 색 변경
                start = (start==1)? 0 : 1;
            }
            // 행이 변경될 때 색 변경
            start = (start==1)? 0 : 1;
        }

        // 첫 번째 칸을 기준으로 할때 색칠 할 개수와 첫 번재 칸의 색의 반대되는 기준으로 할때의
        // 색칠할 개수 중 최솟값을 cnt에 저장
        cnt = Math.min(cnt, 64-cnt);

        // cnt 값이 이전까지의 값들보다 작을경우 min 갱신
        min = Math.min(min, cnt);
    }

}
