package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499_주사위굴리기 {
    static int N, M; // 지도 크기
    static int[] dx = {0, 1, -1, 0, 0}; // 우좌상하
    static int[] dy = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가로
        M = Integer.parseInt(st.nextToken()); // 세로
        int y = Integer.parseInt(st.nextToken()); // 주사위 좌표
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // 명령의 개수

        int[][] map = new int[N][M];
        int[] command = new int[k];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        int[] dice = new int[7];
        for(int i = 0; i < command.length; i++) {
            int ny = y + dy[command[i]];
            int nx = x + dx[command[i]];
            if(!isRange(ny, nx)) {
                y = ny;
                x = nx;
                roll(command[i], dice, map, ny, nx);
            }
        }
    }


    static void roll(int command, int[] dice, int[][] map, int ny, int nx) {
        switch(command) {
            case 1: // 동쪽
                rollEast(map, dice, ny, nx);
                break;
            case 2: // 서쪽
                rollWest(map, dice, ny, nx);
                break;
            case 3: // 북쪽
                rollNorth(map, dice, ny, nx);
                break;
            case 4: // 남쪽
                rollSouth(map, dice, ny, nx);
                break;
        }
    }

    // 북쪽
    static void rollNorth(int[][] map, int[] dice, int ny, int nx) {
        int[] temp = new int[7];
        for(int i = 1; i < dice.length; i++) {
            temp[i] = dice[i];
        }
        dice[2] = temp[1];
        dice[6] = temp[2];
        dice[5] = temp[6];
        dice[1] = temp[5];

        changeMapDice(map, dice, ny, nx);
    }

    // 남쪽
    static void rollSouth(int[][] map, int[] dice, int ny, int nx) {
        int[] temp = new int[7];
        for(int i = 1; i < dice.length; i++) {
            temp[i] = dice[i];
        }
        dice[2] = temp[6];
        dice[6] = temp[5];
        dice[5] = temp[1];
        dice[1] = temp[2];

        changeMapDice(map, dice, ny, nx);
    }

    // 서쪽
    static void rollWest(int[][] map, int[] dice, int ny, int nx) {
        int[] temp = new int[7];
        for(int i = 1; i < dice.length; i++) {
            temp[i] = dice[i];
        }
        dice[4] = temp[1];
        dice[3] = temp[6];
        dice[6] = temp[4];
        dice[1] = temp[3];

        changeMapDice(map, dice, ny, nx);
    }

    // 동쪽
    static void rollEast(int[][] map, int[] dice, int ny, int nx) {
        int[] temp = new int[7];
        for(int i = 1; i < dice.length; i++) {
            temp[i] = dice[i];
        }
        dice[3] = temp[1];
        dice[6] = temp[3];
        dice[4] = temp[6];
        dice[1] = temp[4];

        changeMapDice(map, dice, ny, nx);
    }

    // 지도와 주사위의 바닥 값 변경
    static void changeMapDice(int[][] map, int[] dice, int ny, int nx) {
        if(map[ny][nx] != 0) {
            dice[6] = map[ny][nx];
            map[ny][nx] = 0;
        } else { // 지도 바닥 0인 경우
            map[ny][nx] = dice[6];
        }
        System.out.println(dice[1]);
    }

    // 범위 체크
    static boolean isRange(int y, int x) {
        return y >= N || y < 0 || x >= M || x < 0;
    }
}
