package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2178_미로탐색 {
    static int N, M;
    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map;
    static boolean[][] visited;

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());;
        M = Integer.parseInt(st.nextToken());;

        // 초기화
        map = new int[N][M];
        visited = new boolean[N][M];

        // map 입력
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j=0; j<M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        visited[0][0] = true;
        bfs(0, 0);

        // 방문배열의 마지막 원소 즉, 도착지점의 값 → 최단경로
        System.out.println(map[N-1][M-1]);
    }

    static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList();
        q.add(new Pair(x, y));

        while(!q.isEmpty()) {
            Pair cur = q.poll(); // 다음 방문할 좌표 꺼내기

            for(int d=0; d<4; d++) { // 상하좌우 검사
                int nx = dx[d] + cur.x;
                int ny = dy[d] + cur.y;

                // 다음 방문할 곳이 범위를 넘어가면 건너뛰기
                if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                // 다음 방문할 곳이 방문했던 곳이거나, 이동할 수 없는 곳이면 건너뛰기기
                if(visited[nx][ny] || map[nx][ny]==0) continue;

                // 다음 방문 좌표 넣기
                q.add(new Pair(nx, ny));

                // 다음 좌표는 현재 좌표보다 1칸 더 가야하므로 +1
                map[nx][ny] = map[cur.x][cur.y] + 1;
                // 방문 처리
                visited[nx][ny] = true;
            }
        }
    }
}
