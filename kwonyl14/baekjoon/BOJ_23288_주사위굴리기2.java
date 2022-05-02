package day220411;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23288_주사위굴리기2 {


    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, K, map[][], r, c, d;

    static boolean visited[][];
    static int score[][];

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[] dice = {6, 3, 4, 5, 2, 1}; //밑 동 서 남 북 위

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        score = new int[N][M]; //스코어를 중복계산하지 않기 위해 점수를 메모해놓는 2차원 배열
        r = 0;
        c = 0;
        d = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (K --> 0) {
            play();
        }
        System.out.println(answer);
    }
    static int answer = 0;
    private static void play() {
        //현재 방향으로 굴린다.
        int nr = r + dr[d];
        int nc = c + dc[d];
        //만약 맵을 나가면 반대방향으로 돌아간다.
        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
            d = (d+2)%4;
            nr = r + dr[d];
            nc = c + dc[d];
        }

        //굴린 방향에 따라 전개도를 변경한다.
        if (d == 0) diceEast();
        else if (d == 1) diceSouth();
        else if (d == 2) diceWest();
        else if (d == 3) diceNorth();

        //점수를 획득한다.
        answer += getScore(nr, nc);

        //아랫면과 현재 칸을 비교해 다음 방향을 결정한다.
        if (dice[0] > map[nr][nc]) d = (d+1)%4;
        else if (dice[0] < map[nr][nc]) d = (d+3)%4;

        //굴리기가 확정됐으면 현재위치 r, c 를 업데이트해준다.
        r = nr;
        c = nc;
    }

    private static int getScore(int r, int c) {
        if (score[r][c] != 0) {
            return score[r][c];
        }
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        visited = new boolean[N][M];
        visited[r][c] = true;
        List<Point> scoreList = new ArrayList<>();
        scoreList.add(new Point(r, c));
        int cnt = 1;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != map[r][c]) continue;
                visited[nr][nc] = true;
                cnt++;
                scoreList.add(new Point(nr, nc));

                q.offer(new Point(nr, nc));
            }
        }
        int nowScore = cnt * map[r][c];
        for (Point point : scoreList) {
            score[point.r][point.c] = nowScore;
        }
        return nowScore;
    }

    //                    0  1  2 3 4  5
    //{6, 3, 4, 5, 2, 1}; 밑 동 서 남 북 위
    private static void diceEast() {
        //동쪽으로 굴렸으면 아래에 오른쪽면, 오른쪽엔 윗면, 윗면엔 왼쪽면, 왼쪽엔 밑면이 오게됨
        int temp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[2];
        dice[2] = temp;
    }

    private static void diceWest() {
        //서쪽으로 굴렸으면 아래에 왼쪽면, 왼쪽에 윗면, 위쪽에 오른쪽면, 오른쪽에 밑쪽면이 오게됨
        int temp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[5];
        dice[5] = dice[1];
        dice[1] = temp;
    }

    private static void diceSouth() {
        //아래쪽으로 굴렸으면 밑쪽에 남면, 남쪽에 윗면, 윗쪽에 북면, 북쪽에 밑면이 오게됨
        int temp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[4];
        dice[4] = temp;
    }

    private static void diceNorth() {
        //위쪽으로 굴렸으면 밑쪽에 북면, 북쪽에 윗면, 윗쪽에 남면, 남쪽에 밑면이 오게됨
        int temp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[5];
        dice[5] = dice[3];
        dice[3] = temp;
    }
}