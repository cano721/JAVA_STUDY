import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2665_미로만들기 {
    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int map[][];
    static int visited[][];
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, -1, 0, 1};
    static int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = str.charAt(j)-'0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(visited[N-1][N-1]);
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0));
        visited[0][0] = 0;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if(nx>=0 && nx<N && ny>=0 && ny<N && (visited[nx][ny] > visited[cur.x][cur.y])) {
                    if(map[nx][ny] == 1) {
                        visited[nx][ny] = visited[cur.x][cur.y];
                    } else {
                        visited[nx][ny] = visited[cur.x][cur.y] + 1;
                    }
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }
}
