package day220408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1726_로봇 {

    static class Robot {
        int r, c, d;
        int order;

        public Robot(int r, int c, int d, int order) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.order = order;
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "r=" + r +
                    ", c=" + c +
                    ", d=" + d +
                    ", order=" + order +
                    '}';
        }
    }

    static int N, M, map[][], dr[] = {0, 0, 1, -1}, dc[] = {1, -1, 0, 0};
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken())-1;
        int sc = Integer.parseInt(st.nextToken())-1;
        int sd = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        int er = Integer.parseInt(st.nextToken())-1;
        int ec = Integer.parseInt(st.nextToken())-1;
        int ed = Integer.parseInt(st.nextToken())-1;

        int answer = bfs(sr, sc, sd, er, ec, ed);
        System.out.println(answer);
    }

    private static int bfs(int sr, int sc, int sd, int er, int ec, int ed) {
        PriorityQueue<Robot> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.order));
        q.offer(new Robot(sr, sc, sd, 0));
        visited[sr][sc] = true;

        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Robot robot = q.poll();
            if (robot.r == er && robot.c == ec) {
                if (robot.d == 0 && ed == 1 ||
                        robot.d == 1 && ed == 0 ||
                        robot.d == 2 && ed == 3 ||
                        robot.d == 3 && ed == 2) {
                    answer = Math.min(answer, robot.order + 2);
                } else if (robot.d != ed) {
                    answer = Math.min(answer, robot.order + 1);
                } else {
                    answer = Math.min(answer, robot.order);
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= 3; j++) {
                    int nr = robot.r + dr[i]*j;
                    int nc = robot.c + dc[i]*j;

                    if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == 1) break;
                    visited[nr][nc] = true;
                    if (robot.d == 0 && i == 1 ||
                            robot.d == 1 && i == 0 ||
                            robot.d == 2 && i == 3 ||
                            robot.d == 3 && i == 2) {
                        q.offer(new Robot(nr, nc, i, robot.order + 3));
                    } else if (robot.d != i) {
                        q.offer(new Robot(nr, nc, i, robot.order + 2));
                    } else {
                        q.offer(new Robot(nr, nc, i, robot.order + 1));
                    }
                }
            }
        }
        return answer;
    }
}
