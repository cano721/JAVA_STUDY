package day220427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 통나무의 위치를 가운데 좌표 하나와 세로/가로 구분자만 이용해서 컨트롤하고
 * visited배열을 가로 세로까지 고려한 3차원배열로 체크해주면 다른 bfs문제와 유사하게 풀 수 잇따.
 */
public class BOJ_1938_통나무옮기기 {

    static class Point {
        int r, c;
        int isVertical; //세로면 1, 가로면 0
        int dist;
        public Point(int r, int c, int isVertical, int dist) {
            this.r = r;
            this.c = c;
            this.isVertical = isVertical;
            this.dist = dist;
        }
    }

    static int N, map[][];
    static boolean visited[][][]; //visited[1][2][3]: 세로로(1) 2, 3 위치에 간 적이 있는지 유무.
    static Point barrelTree;
    static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[2][N][N];
        int cntB = 0;
        int btr = 0;
        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (c[j] == '1') map[i][j] = 1;
                else if (c[j] == 'B') {
                    cntB++;
                    if (cntB == 1) { //첫 B를 마주했을 때 현재 좌표를 저장하고, 두번째 B를 입력받을땐 첫번째 r, c와 비교해 세로인지 가로인지 판단
                        btr = i;
                    } else if (cntB == 2) {
                        //첫 입력 r과 현재 i가 같다면 가로로 세워진 통나무라는 의미
                        if (btr == i) barrelTree = new Point(i, j, 0, 0);
                        else barrelTree = new Point(i, j, 1, 0);
                    }
                }
                else if (c[j] == 'E') {
                    map[i][j] = 3;
                }
            }
        }

        int answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(barrelTree);
        visited[barrelTree.isVertical][barrelTree.r][barrelTree.c] = true;

        whileLoop:
        while (!queue.isEmpty()) {
            Point p = queue.poll();

            //현재 위치가 E와 같다면 끝
            if ((p.isVertical == 1 && map[p.r - 1][p.c] == 3 && map[p.r][p.c] == 3 && map[p.r + 1][p.c] == 3) ||
                    p.isVertical == 0 && map[p.r][p.c - 1] == 3 && map[p.r][p.c] == 3 && map[p.r][p.c + 1] == 3) {
                return p.dist;
            }


            //세로일 때 상하좌우
            if (p.isVertical == 1) {
                //상, 하
                for (int i = 0; i < 2; i++) {
                    int nr = p.r + dr[i];
                    int nrr = p.r + dr[i] * 2;
                    int nc = p.c + dc[i];
                    int ncc = p.c + dc[i] * 2;

                    if (nrr < 0 || ncc < 0 || nrr >= N || ncc >= N) continue;
                    if (visited[p.isVertical][nr][nc]) continue;
                    if (map[nrr][ncc] == 1) continue;

                    visited[p.isVertical][nr][nc] = true;
                    queue.offer(new Point(nr, nc, p.isVertical, p.dist+1));
                }

                //좌, 우의 (r-1, r, r+1)을 다 봐야한다.
                forLoop:
                for (int i = 2; i < 4; i++) {
                    int nc = p.c + dc[i];
                    if (nc < 0 || nc >= N) continue;
                    if (visited[p.isVertical][p.r][nc]) continue;
                    for (int j = -1; j <= 1; j++) {
                        int nr = p.r + j;

                        if (nr < 0 || nr >= N) continue forLoop;
                        if (map[nr][nc] == 1) continue forLoop;

                    }
                    visited[p.isVertical][p.r][nc] = true;
                    queue.offer(new Point(p.r, nc, p.isVertical, p.dist+1));
                }
            } else {
                //좌, 우
                for (int i = 2; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nrr = p.r + dr[i] * 2;
                    int nc = p.c + dc[i];
                    int ncc = p.c + dc[i] * 2;

                    if (nrr < 0 || ncc < 0 || nrr >= N || ncc >= N) continue;
                    if (visited[p.isVertical][nr][nc]) continue;
                    if (map[nrr][ncc] == 1) continue;

                    visited[p.isVertical][nr][nc] = true;
                    queue.offer(new Point(nr, nc, p.isVertical, p.dist+1));
                }

                //상, 하의 (c-1, c, c+1)을 다 봐야한다.
                forLoop:
                for (int i = 0; i < 2; i++) {
                    int nr = p.r + dr[i];
                    if (nr < 0 || nr >= N) continue;
                    if (visited[p.isVertical][nr][p.c]) continue;
                    for (int j = -1; j <= 1; j++) {
                        int nc = p.c + j;

                        if (nc < 0 || nc >= N) continue forLoop;
                        if (map[nr][nc] == 1) continue forLoop;
                    }
                    visited[p.isVertical][nr][p.c] = true;
                    queue.offer(new Point(nr, p.c, p.isVertical, p.dist+1));
                }
            }

            //회전시켜본다.
            if (p.isVertical == 1) {
                //먼저 방문체크
                if (visited[0][p.r][p.c]) continue whileLoop;

                //세로일 땐 현재 위치 기준으로 왼쪽위 왼쪽 왼쪽밑 오른쪽위 오른쪽 오른쪽밑 검사
                for (int i = -1; i <= 1; i++) {
                    int nr = p.r + i;
                    for (int j = -1; j <= 1; j += 2) {
                        int nc = p.c - j;

                        //이 위치가 범위를 벗어나는지 확인
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue whileLoop;
                        //나무가 있는지 확인
                        if (map[nr][nc] == 1) continue whileLoop;
                    }
                }
                visited[0][p.r][p.c] = true;
                queue.offer(new Point(p.r, p.c, 0, p.dist+1));
            } else {
                //먼저 방문체크
                if (visited[1][p.r][p.c]) continue whileLoop;

                //가로일 땐 현재 위치 기준으로 위쪽, 아래쪽 검사
                for (int i = -1; i <= 1; i++) {
                    int nc = p.c + i;
                    for (int j = -1; j <= 1; j += 2) {
                        int nr = p.r - j;

                        //이 위치가 범위를 벗어나는지 확인
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue whileLoop;
                        //나무가 있는지 확인
                        if (map[nr][nc] == 1) continue whileLoop;
                    }
                }
                visited[1][p.r][p.c] = true;
                queue.offer(new Point(p.r, p.c, 1, p.dist+1));
            }
        }
        return 0;
    }
}
