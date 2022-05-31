package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class baekjoon16954_움직이는_미로_탈출 {
	private static class Pos {

	    public int x;
	    public int y;

	    public Pos(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }
	}

    // '.' 와 '#'가 그려질 map을 static으로 선언
    static char[][] map = new char[8][8];
    // bfs 사용위한 큐 선언
    static Queue<Pos> q = new LinkedList<>();
    // 대각선 포함한 8방향 + 제자리
    static int dx[] = {1, -1, 0, 0, 1, -1, -1, 1, 0};
    static int dy[] = {0, 0, 1, -1, 1, -1, 1, -1, 0};

    public static void main(String[] args) throws IOException {

        // char형태 그대로 map에 저장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                map[i][j] = c[j];
            }
        }

        // 배열처럼 왼쪽 상단을 (0,0)으로 생각하고 시작점인 왼쪽 하단을 (7,0)으로 두기
        Pos start = new Pos(7, 0);
        q.offer(start);
        bfs();
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            // 같은 depth에 해당하는 만큼 큐에서 빼내줘야 down() 함수 호출 가능
            int s = q.size();
            for (int i = 0; i < s; i++) {
                Pos pos = q.poll();
                if (map[pos.x][pos.y] == '#') {
                    continue;
                }
                for (int j = 0; j < 9; j++) {
                    int nx = pos.x + dx[j];
                    int ny = pos.y + dy[j];
                    if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) {
                        continue;
                    }
                    if (nx == 0 && ny == 7) {
                        System.out.println(1);
                        return;
                    }
                    if (map[nx][ny] != '#') {
                        q.offer(new Pos(nx, ny));
                    }
                }
            }
            down();
        }
        System.out.println(0);
    }

    public static void down() {
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                map[i + 1][j] = map[i][j];
            }
        }
        for (int i = 0; i < 8; i++) {
            map[0][i] = '.';
        }
    }
}