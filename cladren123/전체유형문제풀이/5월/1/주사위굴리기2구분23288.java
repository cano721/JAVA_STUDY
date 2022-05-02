package studyGroup.may.may1;


// https://ilmiodiario.tistory.com/m/160

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 주사위굴리기2구분23288 {
    static int N, M, K, map[][], ans = 0, moveDir, r, c;
    static int dr[] = { -1, 0, 1, 0 };
    static int dc[] = { 0, 1, 0, -1 };
    static int dice[][] = {
            { 0, 2, 0, 0 },
            { 4, 1, 3, 6 },
            { 0, 5, 0, 0 },
            { 0, 6, 0, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        moveDir = 1;
        r = 0;
        c = 0;
        for (int i = 0; i < K; i++) {
            move();
        }
        System.out.println(ans);
    }

    private static void move() {
        // 움직여
        int nr = r + dr[moveDir];
        int nc = c + dc[moveDir];
        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
            moveDir = (moveDir + 2) % 4;
            nr = r + dr[moveDir];
            nc = c + dc[moveDir];
        }
        r = nr;
        c = nc;
        if (moveDir == 0) { // 상
            int tmp = dice[0][1];
            for (int i = 0; i < 3; i++) {
                dice[i][1] = dice[i + 1][1];
            }
            dice[3][1] = tmp;
            dice[1][3] = dice[3][1];
        } else if (moveDir == 1) {// 우
            int tmp = dice[1][3];
            for(int j = 3; j > 0; j--) {
                dice[1][j] = dice[1][j-1];
            }
            dice[1][0] = tmp;
            dice[3][1] = dice[1][3];
        } else if (moveDir == 2) {// 하
            int tmp = dice[3][1];
            for (int i = 3; i > 0; i--) {
                dice[i][1] = dice[i - 1][1];
            }
            dice[0][1] = tmp;
            dice[1][3] = dice[3][1];
        } else {// 좌
            int tmp = dice[1][0];
            for(int j = 0; j < 3; j++) {
                dice[1][j] = dice[1][j+1];
            }
            dice[1][3] = tmp;
            dice[3][1] = dice[1][3];
        }

        // 점수 획득
        ans += getScores();
        // 다음에 갈 방향과 위치 정해
        int A = dice[1][3];
        int B = map[r][c];
        if(A > B) {
            moveDir = (moveDir+1) % 4;
        }else if(A < B) {
            moveDir = (moveDir-1 < 0)? 3 : moveDir-1;
        }
    }

    private static int getScores() {
        int B = map[r][c];
        int C = 0;
        boolean visit[][] = new boolean[N][M];
        visit[r][c] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c));
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            C++;
            for (int d = 0; d < 4; d++) {
                int nr = p.x + dr[d];
                int nc = p.y + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || map[nr][nc] != B)
                    continue;
                visit[nr][nc] = true;
                queue.add(new Point(nr, nc));
            }
        }
        return B * C;
    }
}
