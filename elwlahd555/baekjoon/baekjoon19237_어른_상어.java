package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon19237_어른_상어 {
	private static class Shark implements Comparable<Shark> {
		int x, y, number, smell, direction;
		int nextDirect[][];
		int stayNum;

		public Shark(int x, int y, int number, int smell, int direction, int[][] nextDirect) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
			this.smell = smell;
			this.direction = direction;
			this.nextDirect = nextDirect;
		}

		public Shark(int number, int smell, int stayNum) {
			super();
			this.number = number;
			this.smell = smell;
			this.stayNum = stayNum;
		}

		@Override
		public int compareTo(Shark o) {
			return this.number - o.number;
		}
	}

	private static int N, M, K;
	private static Shark map[][];
	// 위, 아래, 왼쪽, 오른쪽
	private static int dx[] = { 0, -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Shark[N][N];
		LinkedList<Shark> shark = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp > 0) {
					map[i][j] = new Shark(temp, K, 2);
					shark.add(new Shark(i, j, temp, K, 0, new int[5][5]));
				}
			}
		}
		Collections.sort(shark);
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			Shark s = shark.poll();
			s.direction = Integer.parseInt(st.nextToken());
			shark.add(s);
		}
		for (int k = 0; k < shark.size(); k++) {
			Shark s = shark.poll();
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					s.nextDirect[i + 1][j + 1] = Integer.parseInt(st.nextToken());
				}
			}
			shark.add(s);
		}
		int time = 0;
		while (shark.size() > 1 && time <= 1000) {
			time++;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != null) {
						map[i][j].smell--;
						map[i][j].stayNum = 0;
					}
					if (map[i][j] != null && map[i][j].smell < 0) {
						map[i][j] = null;
					}
				}
			}
			int size = shark.size();
			for (int i = 0; i < size; i++) {
				Shark s = shark.poll();
				boolean check = false;
				for (int j = 0; j < 4; j++) {
					int x = s.x + dx[s.nextDirect[s.direction][j + 1]];
					int y = s.y + dy[s.nextDirect[s.direction][j + 1]];
					if (x < 0 || x >= N || y < 0 || y >= N) {
						continue;
					}
					if (map[x][y] == null) {
						s.direction = s.nextDirect[s.direction][j + 1];
						map[x][y] = new Shark(s.number, K, 2);
						s.x = x;
						s.y = y;
						shark.add(s);
						check = true;
						break;
					} else if (map[x][y] != null && map[x][y].stayNum==2) {
						check = true;
						break;
					}
				}
				if (!check) {
					for (int j = 0; j < 4; j++) {
						int x = s.x + dx[s.nextDirect[s.direction][j + 1]];
						int y = s.y + dy[s.nextDirect[s.direction][j + 1]];
						if (x < 0 || x >= N || y < 0 || y >= N) {
							continue;
						}
						if (map[x][y] != null && map[x][y].number == s.number) {
							map[x][y].stayNum = 1;
							map[x][y].smell = K;
							s.x = x;
							s.y = y;
							s.direction = s.nextDirect[s.direction][j + 1];
							shark.add(s);
							check = true;
							break;
						}
					}
				}
			}
		}
		if (time > 1000) {
			time = -1;
		}
		System.out.println(time);
	}
}
