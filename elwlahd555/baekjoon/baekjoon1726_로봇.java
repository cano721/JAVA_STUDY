package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon1726_로봇 {
	private static class Point {
		int x, y, direct, cnt;

		public Point(int x, int y, int direct, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.direct = direct;
			this.cnt = cnt;
		}

	}

	private static int dx[] = { 0, 0, 0, 1, -1 };
	private static int dy[] = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int answer = 0;
		boolean visited[][][] = new boolean[M + 1][N + 1][5];
		int map[][] = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		LinkedList<Point> que = new LinkedList<Point>();
		que.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), 0));
		visited[que.peek().x][que.peek().y][que.peek().direct] = true;
		st = new StringTokenizer(br.readLine());
		Point goal = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), 0);
		while (!que.isEmpty()) {
			Point p = que.poll();
			if (goal.x == p.x && goal.y == p.y && goal.direct == p.direct) {
				answer = p.cnt;
				break;
			}

			for (int i = 1; i < 4; i++) {
				int x = p.x + (dx[p.direct] * i);
				int y = p.y + (dy[p.direct] * i);
				if (x <= 0 || y <= 0 || x > M || y > N) continue;
				
				if (map[x][y] == 0) {
					if(!visited[x][y][p.direct]) {
						visited[x][y][p.direct] = true;
						que.add(new Point(x, y, p.direct, p.cnt + 1));
					}
				} else {
					break;
				}
			}
            for (int i = 1; i <= 4; i++) {
                if (p.direct != i && !visited[p.x][p.y][i]) {
                    int turn = 1;
                    if (p.direct == 1) {
                        if (i == 2) {
                            turn++;
                        }
                    } else if (p.direct == 2) {
                        if (i == 1) {
                            turn++;
                        }
                    } else if (p.direct == 3) {
                        if (i == 4) {
                            turn++;
                        }
                    } else {
                        if (i == 3) {
                            turn++;
                        }
                    }

                    visited[p.x][p.y][i] = true;
                    que.add(new Point(p.x, p.y, i, p.cnt + turn));
                }
            }
		}
		System.out.println(answer);
	}
}
