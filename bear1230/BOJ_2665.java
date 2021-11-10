import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] visit;
    static int[][] arr = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(input.readLine());
        map = new int[n][n];
        visit = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
                visit[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(0, 0);
        System.out.println(visit[n - 1][n - 1]);
    }

    static void bfs(int n, int m) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(n, m, 0));
        visit[n][m] = 0;
        
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int k = 0; k < 4; k++) {
                int nn = now.n + arr[0][k];
                int nm = now.m + arr[1][k];
                
                if (check(nn, nm)) {
                    if (map[nn][nm] == 1 && visit[nn][nm] > now.c) {
                        visit[nn][nm] = now.c;
                        q.add(new Point(nn, nm, now.c));
                        
                    } else if (map[nn][nm]==0 && visit[nn][nm] > now.c + 1) {
                        visit[nn][nm] = now.c + 1;
                        q.add(new Point(nn, nm, now.c + 1));
                    }
                }
            }
        }
    }

    static boolean check(int a, int b) {
        return 0 <= a && a < n && 0 <= b && b < n;
    }
    
    static class Point{
        int n,m,c;
        Point(int n, int m, int c) {
            this.n = n;
            this.m = m;
            this.c = c;
        }
    }
}