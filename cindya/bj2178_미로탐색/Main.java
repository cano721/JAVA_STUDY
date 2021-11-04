package cindya.bj2178_미로탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<int[]> q = new LinkedList<>();
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] maze = new int[nm[0]][nm[1]];
        int cnt = 0;

        for(int i = 0; i < nm[0]; i++){
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        q.offer(new int[]{0, 0}); // 시작점을 큐에 넣음
        maze[0][0] = 0; // 넣은 부분은 0으로 바꿔 다시 돌아가지 못하게 함
        while(!q.isEmpty()){ // 큐가 빈 상태이면 루프 멈춤
            int size = q.size();
            for(int i = 0; i < size; i++) { // 같은 깊이에 있는 요소들 확인
                int[] xy= q.poll(); // 큐에서 하나 뽑음
                int x = xy[0], y = xy[1];
                if(x == nm[0] - 1 && y == nm[1] - 1) { // 만약 뽑은 점이 도착점이라면
                    q.clear(); // 큐를 비우고 루프 멈춤
                    break;
                }

                // 뽑은 좌표 주변 확인
                // 위
                if (x > 0 && maze[x - 1][y] == 1) {
                    q.offer(new int[]{x - 1, y});
                    maze[x - 1][y] = 0;
                }
                // 아래
                if (x < nm[0] - 1 && maze[x + 1][y] == 1) {
                    q.offer(new int[]{x + 1, y});
                    maze[x + 1][y] = 0;
                }
                // 왼쪽
                if (y > 0 && maze[x][y - 1] == 1) {
                    q.offer(new int[]{x, y - 1});
                    maze[x][y - 1] = 0;
                }
                // 오른쪽
                if (y < nm[1] - 1 && maze[x][y + 1] == 1) {
                    q.offer(new int[]{x, y + 1});
                    maze[x][y + 1] = 0;
                }
            }
            cnt++; // 깊이 카운트
        }

        System.out.println(cnt);
    }
}
