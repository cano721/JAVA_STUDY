import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int n, m;
    public static int chs;
    public static int arr[][];
    public static int dx[] = {1, -1, 0, 0};
    public static int dy[] = {0, 0, 1, -1};

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer first = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(first.nextToken());
        m = Integer.parseInt(first.nextToken());

        arr = new int[n][];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    chs++;
                }
            }
        }

        int time = 0;

        while (chs != 0) {
            sol();
            time++;
        }

        System.out.println(time);
    }

    
    public static void sol() {
        Queue<Point> q = new LinkedList<>();

        int[][] visited = new int[n][m];

        q.add(new Point(0, 0));
        visited[0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == 1) {
                    visited[nx][ny]++;
                    continue;
                }

                if (visited[nx][ny] != 0) {
                    continue;
                }

                q.add(new Point(nx, ny));
                visited[nx][ny] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] >= 2) {
                    arr[i][j] = 0;
                    chs--;
                }
            }
        }
    }
}