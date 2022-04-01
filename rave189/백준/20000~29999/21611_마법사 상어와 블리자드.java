package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxN 격자에 마법사 상어가 ((N+1)/2, (N+1)/2)에 있다.
 * 격자에는 1, 2, 3번 구슬이 존재한다.
 * 격자는 상어를 기준으로 반시계로 회전하는 토네이도로 모양으로 벽이 쳐져 있다.
 * 24	23	22	21	20
 * 9	8	7	6	19
 * 10	1	상어	5	18
 * 11	2	3	4	17
 * 12	13	14	15	16
 * 상어는 상하좌우로 S만큼 마법을 쏘아 구슬을 없앨 수 있다.
 * 마법은 위에서 내리기 때문에 벽에는 아무 영향을 주지 않는다.
 * 없앤 구슬 만큼 토네이도 모양을 따라 상어쪽으로 구슬이 당겨진다.
 * 구슬이 토네이도 모양으로 4개 이상 모이는 경우 구슬이 폭발한다.
 * 폭발하면 해당 자리는 구슬이 없어진다.
 * 없어진 공간만큼 다시 구슬을 당긴다.
 * 구슬의 폭발이 일어나지 않을때까지 위 과정을 반복한다.
 * 이제 인접한 구슬을 그룹으로 묶어 그룹의 개수, 그룹의 구슬 번호로 1번부터 차례대로 들어간다.
 * 
 * 1번 구슬 * 터진 개수 + 2번 구슬 * 터진 개수 + 3번 구슬 * 터진 개수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static int answer = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] search = { 2, 1, 3, 0 };
	static ArrayList<ArrayList<Point>> bombList = new ArrayList<>();
	static Point shark;

	/**
	 * 주어진대로 구현하는 문제
	 * 탐색을 특이하게 하지만 방법이 생각이 나서 기본 틀은 어느정도 금방 구현했다.
	 * 근데 탐색 도중 if, else 등에서 빼먹거나 더 추가해주어야 하는 조건들 때문에 오래 걸림.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		shark = new Point(n / 2, n / 2);
		map[shark.x][shark.y] = -1;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			blizzard(d, s);
			move();
			while (isBomb()) {
				bomb();
				move();
			}
			groupChange();
		}
		System.out.println(answer);
	}

	public static void blizzard(int d, int s) {
		for (int step = 1; step <= s; step++) {
			int nextX = shark.x + dx[d] * step;
			int nextY = shark.y + dy[d] * step;
			map[nextX][nextY] = 0;
		}
	}

	public static void move() {
		Queue<Point> blinkQ = new LinkedList<>();
		int[] moveCnt = getMoveCount();
		Point cur = new Point(shark.x, shark.y);
		for (int searchIdx = 0,
				moveIdx = 0; moveIdx < moveCnt.length; moveIdx++, searchIdx = (searchIdx + 1) % search.length) {
			while (moveCnt[moveIdx] > 0) {
				moveCnt[moveIdx]--;
				if (map[cur.x][cur.y] == 0)
					blinkQ.add(new Point(cur.x, cur.y));
				else if (!blinkQ.isEmpty()) {
					Point blink = blinkQ.poll();
					map[blink.x][blink.y] = map[cur.x][cur.y];
					map[cur.x][cur.y] = 0;
					blinkQ.add(new Point(cur.x, cur.y));
				}
				int direction = search[searchIdx];
				int nextX = cur.x + dx[direction];
				int nextY = cur.y + dy[direction];
				cur.move(nextX, nextY);
			}
		}
	}

	public static int[] getMoveCount() {
		int[] arr = new int[map.length * 2 - 1];
		for (int i = 0, j = 1; i < arr.length; i++) {
			arr[i] = j;
			if (i % 2 == 1)
				j++;
		}
		return arr;
	}

	public static boolean isBomb() {
		ArrayList<Point> group = new ArrayList<>();
		int[] moveCnt = getMoveCount();
		Point cur = new Point(shark.x, shark.y);
		for (int searchIdx = 0,
				moveIdx = 0; moveIdx < moveCnt.length; moveIdx++, searchIdx = (searchIdx + 1) % search.length) {
			while (moveCnt[moveIdx] > 0) {
				moveCnt[moveIdx]--;
				if (map[cur.x][cur.y] > 0) {
					if (group.isEmpty())
						group.add(new Point(cur.x, cur.y));
					else {
						Point p = group.get(0);
						if (map[p.x][p.y] != map[cur.x][cur.y]) {
							if (group.size() >= 4) {
								bombList.add(group);
								group = new ArrayList<>();
							}
							group.clear();
						}
						group.add(new Point(cur.x, cur.y));
					}
				}
				int direction = search[searchIdx];
				int nextX = cur.x + dx[direction];
				int nextY = cur.y + dy[direction];
				cur.move(nextX, nextY);
			}
		}
		if (group.size() >= 4)
			bombList.add(group);
		return !bombList.isEmpty();
	}

	public static void bomb() {
		for (ArrayList<Point> bomb : bombList) {
			for (Point p : bomb) {
				answer += map[p.x][p.y];
				map[p.x][p.y] = 0;
			}
		}
		bombList.clear();
	}

	public static void groupChange() {
		ArrayList<Group> groupList = getGroupList();
		int[] moveCnt = getMoveCount();
		Point cur = new Point(shark.x, shark.y);
		for (int searchIdx = 0, moveIdx = 0, groupIdx = 0,
				count = 0; moveIdx < moveCnt.length; moveIdx++, searchIdx = (searchIdx + 1) % search.length) {
			while (moveCnt[moveIdx] > 0) {
				moveCnt[moveIdx]--;
				if (groupIdx < groupList.size()) {
					if (map[cur.x][cur.y] >= 0) {
						if (count++ % 2 == 0)
							map[cur.x][cur.y] = groupList.get(groupIdx).count;
						else
							map[cur.x][cur.y] = groupList.get(groupIdx++).value;
					}
				} else
					map[cur.x][cur.y] = 0;
				int direction = search[searchIdx];
				int nextX = cur.x + dx[direction];
				int nextY = cur.y + dy[direction];
				cur.move(nextX, nextY);
			}
		}
	}

	public static ArrayList<Group> getGroupList() {
		ArrayList<Group> groupList = new ArrayList<>();
		Group group = null;
		int[] moveCnt = getMoveCount();
		Point cur = new Point(shark.x, shark.y);
		for (int searchIdx = 0,
				moveIdx = 0; moveIdx < moveCnt.length; moveIdx++, searchIdx = (searchIdx + 1) % search.length) {
			while (moveCnt[moveIdx] > 0) {
				moveCnt[moveIdx]--;
				if (map[cur.x][cur.y] > 0) {
					if (group == null)
						group = new Group(map[cur.x][cur.y], 1);
					else if (group.value != map[cur.x][cur.y]) {
						groupList.add(group);
						group = new Group(map[cur.x][cur.y], 1);
					} else
						group.plus();
				}
				int direction = search[searchIdx];
				int nextX = cur.x + dx[direction];
				int nextY = cur.y + dy[direction];
				cur.move(nextX, nextY);
			}
		}
		if (group != null)
			groupList.add(group);
		return groupList;
	}
}

class Group {
	int value, count;

	public Group(int value, int count) {
		this.value = value;
		this.count = count;
	}

	public void plus() {
		count++;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int nextX, int nextY) {
		this.x = nextX;
		this.y = nextY;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}