import java.util.*;

/**
 * 이번 dfs, bfs는 이해했다고 생각했는데...아직 미흡했나봅니다...
 * 도저히 감이 안잡혀서 구글링하였고,,, 코드를 어느정도 이해는 했으나 dfs bfs 기본 개념이 잡히지않아 지난주 과제는 좀 많이 힘듷었던것 같습니다..
 * */

public class BJ16197 {

    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] board;
    static Coin[] coin; // 동전의 위치 저장.
    static boolean[][][][] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();

        board = new char[n][m];
        coin = new Coin[2];
        int coinIdx = 0;
        for(int i = 0; i < n; i++) {
            String str = scan.next();
            for(int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if(c == 'o') {
                    coin[coinIdx++] = new Coin(i, j); //코인의 위치 저장
                }
                board[i][j] = c;
            }
        }

        visited = new boolean[n][m][n][m]; //[첫번째동전의x위치][첫번째동전의y위치][두번째동전의x위치][두번째동전의y위치]
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<towCoins> q = new LinkedList<>();
        q.offer(new towCoins(coin[0].x, coin[0].y, coin[1].x, coin[1].y, 0));
        visited[coin[0].x][coin[0].y][coin[1].x][coin[1].y] = true;

        while(!q.isEmpty()) {
            towCoins current = q.poll();

            if(current.count >= 10) break;

            for(int i = 0; i < 4; i++) {
                int nx1 = current.x1 + dx[i];
                int ny1 = current.y1 + dy[i];
                int nx2 = current.x2 + dx[i];
                int ny2 = current.y2 + dy[i];

                //이동할 좌표가 벽이면 이동할 수 없으므로 이동하지 않는다.
                if(!canMoveCoin(nx1, ny1)) {
                    nx1 = current.x1;
                    ny1 = current.y1;
                }
                if(!canMoveCoin(nx2, ny2)) {
                    nx2 = current.x2;
                    ny2 = current.y2;
                }

                int flag = 0; //떨어지지 않는 동전의 개수
                if(nx1 >= 0 && ny1 >= 0 && nx1 < n && ny1 < m) flag++;
                if(nx2 >= 0 && ny2 >= 0 && nx2 < n && ny2 < m) flag++;

                if(flag == 1) return current.count + 1;
                else if(flag == 2 && !visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.offer(new towCoins(nx1, ny1, nx2, ny2, current.count + 1));
                }
            }
        }
        return -1;
    }

    public static boolean canMoveCoin(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < n && ny < m && board[nx][ny] == '#') {
            return false;
        }
        return true;
    }

    public static class towCoins { //두 동전의 위치와 현재 버튼을 누른 횟수를 기록하는 객체
        int x1;
        int y1;
        int x2;
        int y2;
        int count;

        public towCoins(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }
    }

    public static class Coin { //동전의 좌표를 기억하는 객체
        int x;
        int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
