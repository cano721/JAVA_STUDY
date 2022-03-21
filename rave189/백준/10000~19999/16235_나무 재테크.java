package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[][] addMap, map, removeMap;
	static PriorityQueue<Integer>[][] pq;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		addMap = new int[n][n];
		map = new int[n][n];
		pq = new PriorityQueue[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				addMap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
				pq[i][j] = new PriorityQueue<>();
			}
		}
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			pq[x][y].add(age);
		}
		while (k-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}
		long answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer += pq[i][j].size();
			}
		}
		System.out.println(answer);
	}

	public static void spring() {
		removeMap = new int[pq.length][pq[0].length];
		ArrayList<Integer> growTree = new ArrayList<>();
		for (int i = 0; i < pq.length; i++) {
			for (int j = 0; j < pq[0].length; j++) {
				while (!pq[i][j].isEmpty()) {
					if (pq[i][j].peek() <= map[i][j]) {
						int age = pq[i][j].poll();
						growTree.add(age + 1);
						map[i][j] -= age;
					} else {
						removeMap[i][j] += pq[i][j].poll() / 2;
					}
				}
				pq[i][j].addAll(growTree);
				growTree.clear();
			}
		}
	}

	public static void summer() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] += removeMap[i][j];
			}
		}
	}

	public static void fall() {
		for (int i = 0; i < pq.length; i++) {
			for (int j = 0; j < pq[0].length; j++) {
				for (int treeAge : pq[i][j]) {
					if (treeAge % 5 == 0) {
						for (int t = 0; t < dx.length; t++) {
							int nextX = i + dx[t];
							int nextY = j + dy[t];
							try {
								pq[nextX][nextY].add(1);
							} catch (ArrayIndexOutOfBoundsException e) {
							}
						}
					}
				}
			}
		}
	}

	public static void winter() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] += addMap[i][j];
			}
		}
	}
}