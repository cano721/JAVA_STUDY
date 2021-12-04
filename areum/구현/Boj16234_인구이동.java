package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16234_인구이동 {
    static int N, L, R, day;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 입력받을 map 크기
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            boolean isBfs = false;

            visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { // 이미 연합을 이루었던 경우는 넘어감
                        if (bfs(i, j))
                            isBfs = true;
                    }

                }
            }

            if(!isBfs) {
                break;
            } else {
                day++;
            }
        }

        System.out.println(day);
    }

    public static boolean bfs(int x, int y) {
        boolean isUnion = false;

        ArrayList<Point> unionList = new ArrayList<>(); // 연합에 속하는 나라의 좌표 리스트
        unionList.add(new Point(x, y));
        int count = 1;
        int sum = map[x][y];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Point p = queue.poll();
            int curX = p.x;
            int curY = p.y;

            for(int i = 0; i < 4; i++) { // 상좌하우 검사
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) // 범위 체크
                    continue;

                if(visited[nx][ny])
                    continue;

                // 두 나라의 인구 차이가 L명 이상, R명 이하라면 연합 가능
                int val = Math.abs(map[curX][curY] - map[nx][ny]);

                if(val >= L && val <= R && !visited[nx][ny]) {
                    unionList.add(new Point(nx, ny));
                    count++;
                    sum += map[nx][ny];

                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }

        if(unionList.size() > 1) {
            isUnion = true;
            int result = sum / count;

            for(int i = 0; i < unionList.size(); i++) { // 연합 인구수 업데이트
                Point p = unionList.get(i);
                map[p.x][p.y] = result;
            }
        }
        return isUnion;
    }
}



