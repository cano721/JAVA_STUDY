package cindya.bj1261_알고스팟;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 2665 미로 만들기와 거의 비슷함
public class Main {
    private static final int[] moveRow = {-1, 1, 0, 0}, moveCol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken(" ")), n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][], walls = new int[n][m];
        boolean[][] queueCheck = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(walls[i], Integer.MAX_VALUE); // 깨야할 벽 개수를 가장 큰 정수로 세팅
        }
        br.close();

        // 시작점 q에 넣고, 벽 개수를 0으로 세팅
        q.offer(new int[]{0, 0});
        walls[0][0] = 0;

        while (!q.isEmpty()){
            int[] room = q.poll();
            int x = room[0];
            int y = room[1];
            queueCheck[x][y] = false; // q에서 뺐다는 것을 표시

            // 사방의 방을 확인
            for(int i = 0; i < 4; i++){
                int r = x + moveRow[i], c = y + moveCol[i];
                // 방이 맵 안에 있고, (x, y) 지점에서 가는 것보다 깰 벽 개수가 많으면
                if(0 <= r && r < n && 0 <= c && c < m && walls[x][y] + map[r][c] < walls[r][c]){
                    walls[r][c] = walls[x][y] + map[r][c]; // 값 변경
                    if(!queueCheck[r][c]) { // q에 들어있지 않으면
                        q.offer(new int[]{r, c}); // q에 삽입
                        queueCheck[r][c] = true; // 삽입 표시
                    }
                }
            }
        }

        System.out.println(walls[n - 1][m - 1]);
    }
}
