package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 연구소에 바이러스가 있다.
 * M개의 바이러스를 활성화하여 연구소의 모든 구역을 감염시키려고 한다.
 * 활성 바이러스는 상하좌우로 1초마다 퍼진다.
 * 벽은 넘어갈 수 없다.
 * 연구소가 감염되는 최소 시간을 구하는 문제
 * 감염시키지 못한다면 -1을 출력한다.
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static ArrayList<Virus> viruses = new ArrayList<>();
	static Virus[] selected;
	static int m, zeroCnt = 0, answer = Integer.MAX_VALUE;

	/**
	 * 구현
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		selected = new Virus[m];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					zeroCnt++;
				else if (map[i][j] == 2)
					viruses.add(new Virus(i, j));
			}
		}
		bruteforce(0, 0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	public static void bruteforce(int depth, int cur) {
		if (depth == m) {
			calc();
			return;
		}

		for (int i = cur; i < viruses.size(); i++) {
			selected[depth] = viruses.get(i);
			bruteforce(depth + 1, i + 1);
		}
	}

	public static void calc() {
		Queue<Virus> q = new LinkedList<>();
		boolean[][] visited = new boolean[map.length][map[0].length];
		for (Virus cur : selected) {
			visited[cur.x][cur.y] = true;
			q.add(cur);
		}
		int time = 0;
		int tmpZeroCnt = zeroCnt;
		while (!q.isEmpty() && tmpZeroCnt > 0) {
			int size = q.size();
			while (size-- > 0) {
				Virus cur = q.poll();
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (map[nextX][nextY] == 1 || visited[nextX][nextY])
							continue;
						visited[nextX][nextY] = true;
						q.add(new Virus(nextX, nextY));
						if (map[nextX][nextY] == 0)
							tmpZeroCnt--;
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
			time++;
		}
		if (tmpZeroCnt == 0)
			answer = Math.min(answer, time);
	}
}

class Virus {
	int x, y;

	public Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}