package day2204.day220408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954_움직이는미로탈출 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int
            dr[] = {
            0,
            -1, 1, 0, 0,
            -1, 1, 1, -1
    },
            dc[] = {
            0,
            0, 0, 1, -1,
            -1, 1, -1, 1
    };
    static char map[][];
    static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];

        for (int i = 0; i < 8; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                map[i][j] = chars[j];
            }
        }
        int sr = 7;
        int sc = 0;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(sr, sc));

        int answer = 0;
        Loop:
        while (!q.isEmpty()) {
            // 욱제가 움직인다.
            int size = q.size();
            visited = new boolean[8][8];

            while (size --> 0) {
                Point p = q.poll();
                if (p.r == 0 && p.c == 7) {
                    answer = 1;
                    break Loop;
                }

                if (map[p.r][p.c] == '#') {
                    continue;
                }

                for (int i = 0; i < 9; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (nr < 0 || nc < 0 || nr >= 8 || nc >= 8) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == '.' && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new Point(nr, nc));
                    }
                }
            }

            //내려올 벽 탐색
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    if (map[i][j] == '#') {
                        if (i == 7) map[i][j] = '.';
                        else {
                            //벽이 내려오고
                            map[i+1][j] = map[i][j];
                            map[i][j] = '.';
                            //그 위치에 있는 욱제는 컷
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
