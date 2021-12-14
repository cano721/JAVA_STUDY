package cindya.bj4485_녹색옷입은애가젤다지;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 1261 알고스팟과 같은 문제
public class Main {
    private static final int[] upDown = {-1, 1, 0, 0}, leftRight = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n, cnt = 1;

        while ((n = Integer.parseInt(br.readLine())) != 0){
            int[][] map = new int[n][], rupee = new int[n][n];
            boolean[][] qCheck = new boolean[n][n];
            Queue<int[]> q = new LinkedList<>();

            for(int i = 0; i < n; i++){
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(rupee[i], Integer.MAX_VALUE);
            }

            qCheck[0][0] = true;
            rupee[0][0] = map[0][0];
            q.offer(new int[]{0, 0});

            while (!q.isEmpty()){
                int x = q.peek()[0], y = q.poll()[1];
                qCheck[x][y] = false;

                for(int i = 0; i < 4; i++){
                    int r = x + upDown[i], c = y + leftRight[i];
                    if(0 <= r && r < n && 0 <= c && c < n && rupee[r][c] > rupee[x][y] + map[r][c]){
                        rupee[r][c] = rupee[x][y] + map[r][c];
                        if(!qCheck[r][c]) {
                            q.offer(new int[]{r, c});
                            qCheck[r][c] = true;
                        }
                    }
                }
            }
            bw.write(String.format("Problem %d: %d\n", cnt++, rupee[n - 1][n - 1]));
        }

        br.close();
        bw.close();
    }
}
