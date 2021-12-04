package baekjoon;

// https://moonsbeen.tistory.com/12
import java.util.Scanner;

/*
로봇 청소기가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.

로봇 청소기가 있는 장소는 N×M 크기의 직사각형으로 나타낼 수 있으며, 1×1크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다. 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북중 하나이다. 지도의 각 칸은 (r, c)로 나타낼 수 있고, r은 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로 부터 떨어진 칸의 개수이다.

로봇 청소기는 다음과 같이 작동한다.

    현재 위치를 청소한다.
    현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다.
        왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
        왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
        네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
        네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.

로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.

입력
첫째 줄에 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 50)
둘째 줄에 로봇 청소기가 있는 칸의 좌표 (r, c)와 바라보는 방향 d가 주어진다. d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
셋째 줄부터 N개의 줄에 장소의 상태가 북쪽부터 남쪽 순서대로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 빈 칸은 0, 벽은 1로 주어진다. 지도의 첫 행, 마지막 행, 첫 열, 마지막 열에 있는 모든 칸은 벽이다.
로봇 청소기가 있는 칸의 상태는 항상 빈 칸이다.

출력
로봇 청소기가 청소하는 칸의 개수를 출력한다.


3 3
1 1 0
1 1 1
1 0 1
1 1 1

1


*/

public class Boj14503_로봇청소기 {
    static int n, m, r, c, d;
    static int[][] board;
    static int count = 1; // 시작 지점은 항상 청소한다.
    static int[] dx = { -1, 0, 1, 0 }; // 북, 동, 남, 서 순서대로
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();
        r = scan.nextInt();
        c = scan.nextInt();
        d = scan.nextInt();

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scan.nextInt();
            }
        }

        dfs(r, c, d);
        System.out.println(count);
    }

    public static void dfs(int x, int y, int dir) {
        board[x][y] = 2; // 청소 했다는 의미

        for (int i = 0; i < 4; i++) {
            dir -= 1; // 왼쪽 방향으로 돌면서 탐색
            if (dir == -1)
                dir = 3;

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (board[nx][ny] == 0) { // 벽도 아니고 이미 청소한 곳도 아니라면 청소하러 이동한다
                    count++;
                    dfs(nx, ny, dir);
                    // 일반적인 dfs는 가다가 길이 막히면 다시 되돌아와서 해당 위치부터 계산하지만, 이 문제는 후진할 때만 이전 길을 되돌가 가며 확인할 수
                    // 있으므로 return을 해서 다시 되돌아 와도 더 이상 움직이면 안된다.
                    return;
                }
            }
        }

        // 반목문을 빠져 나왔단는 것은 주변에 더 이상 청소할 공간이 없다는 의미이다.
        int d = (dir + 2) % 4; // 반대 방향으로 후진하기 위함.
        int bx = x + dx[d];// 후진
        int by = y + dy[d];// 후진
        if (bx >= 0 && by >= 0 && bx < n && by < m && board[bx][by] != 1) {
            dfs(bx, by, dir); // 후진할 때 방향을 유지해야 한다.
        }
    }
}
