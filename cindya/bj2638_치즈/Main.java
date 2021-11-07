package cindya.bj2638_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] rowCheck = {-1, 1, 0, 0}, colCheck = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] board = new int[nm[0]][nm[1]];
        int cnt = 0, zeroCnt = 0;

        for (int i = 0; i < nm[0]; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        // 치즈 안쪽 공기 = 2, 치즈 바깥쪽 공기 = 3 으로 변경
        for(int i = 0; i < nm[0]; i++){
            for(int j = 0; j < nm[1]; j++){
                if(board[i][j] == 0){ // 빈 칸이 나오면
                    int s = 2;
                    Queue<int[]> q = new LinkedList<>();
                    List<int[]> l = new ArrayList<>();
                    q.offer(new int[]{i, j});
                    board[i][j] = s;

                    // 주변 빈 칸 탐색
                    while (!q.isEmpty()){
                        int[] xy = q.poll();
                        int x = xy[0], y = xy[1];

                        for(int t = 0; t < 4; t++){
                            int r = x + rowCheck[t], c = y + colCheck[t];
                            if(0 <= r && r < nm[0] && 0 <= c && c < nm[1] && board[r][c] == 0){
                                // 가장자리가 포함된 빈 칸일 경우 바깥쪽 빈 칸이므로 3으로 변경
                                if(r == 0 || c == 0) {
                                    s = 3;
                                    for (int[] b : l) {
                                        board[b[0]][b[1]] = s;
                                    }
                                }
                                board[r][c] = s;
                                q.offer(new int[]{r, c});
                            }
                        }
                        l.add(xy);
                    }
                    zeroCnt += l.size(); // 빈 칸 개수 카운트
                }
            }
        }

        // 치즈 녹이기
        while(true){
            List<int[]> l = new ArrayList<>();
            // 바깥쪽 치즈 녹이기 (0으로 표시)
            for(int i = 1; i < nm[0] - 1; i++){
                for(int j = 1; j < nm[1] - 1; j++){
                    if(board[i][j] == 1){
                        int closeBlanks = 0;
                        // 바깥 공기와 닿은 부분 카운트
                        for(int t = 0; t < 4; t++){
                            if(board[i + rowCheck[t]][j + colCheck[t]] == 3)
                                closeBlanks++;
                        }
                        // 한칸 이상 접촉 시 녹이고 빈 칸 카운트 증가
                        if(closeBlanks > 1) {
                            board[i][j] = 0;
                            l.add(new int[]{i, j});
                            zeroCnt++;
                        }
                    }
                }
            }
            cnt++; // 시간 증가
            if(zeroCnt == nm[0] * nm[1]) break; // 전체가 빈 칸이면 중지

            // 안쪽 빈 칸 중 바깥 빈 칸과 닿은 것이 있으면 0으로 표시
            for(int i = 1; i < nm[0] - 1; i++){
                for(int j = 1; j < nm[1] - 1; j++){
                    if(board[i][j] == 2){
                        int closeBlanks = 0;
                        // 바깥 공기와 닿은 부분 카운트
                        for(int t = 0; t < 4; t++){
                            if(board[i + rowCheck[t]][j + colCheck[t]] == 0)
                                closeBlanks++;
                        }
                        if(closeBlanks > 0) { // 닿았으면
                            Queue<int[]> q = new LinkedList<>();
                            q.offer(new int[]{i, j});
                            board[i][j] = 0;

                            // 닿은 부분과 같은 구역을 모두 0으로 표시
                            while (!q.isEmpty()){
                                int[] xy = q.poll();
                                for(int t = 0; t < 4; t++){
                                    int r = xy[0] + rowCheck[t], c = xy[1] + colCheck[t];
                                    if(0 <= r && r < nm[0] && 0 <= c && c < nm[1] && board[r][c] == 2) {
                                        board[r][c] = 0;
                                        q.offer(new int[]{r, c});
                                        closeBlanks++;
                                    }
                                }
                                l.add(xy);
                            }
                        }
                    }
                }
            }
            // 0으로 표시된 부분들 3(바깥 공기)로 변경
            for(int[] b : l){
                board[b[0]][b[1]] = 3;
            }
        }
        System.out.println(cnt);
    }
}