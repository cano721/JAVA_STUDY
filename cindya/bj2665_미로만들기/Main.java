package cindya.bj2665_미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] maze = new int[n][n], dijkstra = new int[n][n];
        int[] rowMove = {-1, 1, 0, 0}, colMove = {0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dijkstra[i], Integer.MAX_VALUE);
        }
        br.close();

        q.offer(new int[]{0, 0}); // q에 시작점 넣기
        dijkstra[0][0] = 0; // 시작점의 길이도 0으로 설정

        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0], y = xy[1];
            // q에서 꺼낸 지점의 사방을 확인
            for (int i = 0; i < 4; i++) {
                int r = x + rowMove[i], c = y + colMove[i];
                // 미로를 벗어나지 않는 점이고, 바꿔야하는 방의 수가 x,y보다 많다면
                if(0 <= r && r < n && 0 <= c && c < n && dijkstra[x][y] < dijkstra[r][c]){
                    switch (maze[r][c]){
                        case 0: // 이 지점이 까만방인 경우 + 1
                            dijkstra[r][c] = dijkstra[x][y] + 1;
                            break;
                        case 1: // 이 지점이 하얀방일 경우 이전과 같은 횟수로 세팅
                            dijkstra[r][c] = dijkstra[x][y];
                            break;
                    }
                    // q에 넣기
                    q.offer(new int[]{r, c});
                }
            }
        }
        System.out.println(dijkstra[n-1][n-1]);
    }
}
