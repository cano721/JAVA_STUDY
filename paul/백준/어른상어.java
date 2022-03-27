import java.util.*;
import java.io.*;

public class Main {

    static class shark {
        int dir;
        // 위, 아래, 왼, 오
        int[][] order = new int[4][4];
    }

    static class pair {
        int y, x;

        public pair() {
            y = x = 0;
        }

        public pair(int yy, int xx) {
            y = yy;
            x = xx;
        }
    }

    static int[][] board = new int[22][22];
    static pair[][] area = new pair[22][22];
    static int n, m, k, ans; // 크기, 상어, 냄새 사라지는 시간.

    static shark[] sh = new shark[402];
    static pair[] pos = new pair[402]; // 상어의 번호별 위치를 저장하는 영역.

    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    // 이동할 방향 정하기.
    static void selectdir(int now) {

        for (int i = 1; i <= m; i++) {
            // i번 상어 방향 탐색.
            int dir = sh[i].dir;
            if (dir == -1)
                continue;
            boolean flag = false;

            int y = pos[i].y, x = pos[i].x;

            for (int k = 0; k < 4; k++) {
                int idx = sh[i].order[dir][k];
                int cy = y + dy[idx], cx = x + dx[idx];

                if (cy < 0 || cy >= n || cx < 0 || cx >= n)
                    continue;
                // 냄새가 없는 칸인지 확인
                if (now - area[cy][cx].y <= 0)
                    continue;

                // 방향 정해.
                sh[i].dir = idx;
                flag = true;
                break;
            }

            // 냄새가 없는 칸이 없어
            if (flag == false) {
                // 내 냄새가 있는 곳으로 이동
                for (int k = 0; k < 4; k++) {
                    int idx = sh[i].order[dir][k];
                    int cy = y + dy[idx], cx = x + dx[idx];

                    if (cy < 0 || cy >= n || cx < 0 || cx >= n)
                        continue;
                    // 내 냄새가 배긴곳인지 확인
                    if (i != area[cy][cx].x)
                        continue;

                    // 방향 정해.
                    sh[i].dir = idx;
                    flag = true;
                    break;
                }
            }
        }
    }

    // now는 현재 시간.
    static void move(int now) {
        // 자기 방향으로 이동
        boolean[][] temp = new boolean[n][n];

        for (int i = 1; i <= m; i++) {
            int dir = sh[i].dir;
            if (dir == -1)
                continue;
            int y = pos[i].y + dy[dir], x = pos[i].x + dx[dir];

            // 이미 존재하는게 있다? 나는 날라가야함.. 순서대로 오니까
            if (temp[y][x]) {
                sh[i].dir = -1;
                board[pos[i].y][pos[i].x] = 0;
                pos[i].y = pos[i].x = -1;
                ans--;
            } else {
                // 이동
                temp[y][x] = true;

                board[pos[i].y][pos[i].x] = 0;
                pos[i].y = y;
                pos[i].x = x;
                board[y][x] = i;
                area[y][x].y = now + k;
                area[y][x].x = i;
            }

        }
    }

    static void pro() {

        for (int time = 1; time < 1001; time++) {
            selectdir(time);
            move(time);

            if (ans == 1) {
                System.out.println(time);
                break;
            }
        }

        if (ans != 1) {
            System.out.println(-1);
        }

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //init
        for(int i =0; i<402; i++){
            sh[i] = new shark();
            pos[i] = new pair();
        }
        
        for(int i =0; i< 22; i++){
            for(int j =0; j<22; j++){
                area[i][j] = new pair(); 
            }
        }
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = m;
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > 0) {
                    int idx = board[i][j];
                    pos[idx] = new pair(i,j);
                    //초기 영역표시.
                    area[i][j].y = k;
                    area[i][j].x = idx;
                }
            }
        }
    
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            //상어 방향입력받기
            
            sh[i].dir = Integer.parseInt(st.nextToken());
            sh[i].dir--;
        }
    
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sh[i].order[j][k] = Integer.parseInt(st.nextToken());
                    sh[i].order[j][k]--;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

}