package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxN 격자가 있다.
 * 각 격자에는 블록이 하나씩있고, 블록은 검은색, 무지개, 일반 블록이 있다.
 * 일반 블록은 M가지 색이 있고, M이하의 자연수로 표현된다.
 * 검은색은 -1, 무지개는 0으로 표현된다.
 * 블록그룹은 상하좌우로 인접한 블록의 집합이다.
 * 단 이 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 한다.
 * 또한, 검은색 블록은 없어야 하고, 무지개 블록은 여러 개여도 상관 없다.
 * 그리고 그룹의 크기는 2 이상이어야 한다.
 * 그룹에는 기준 블록이 있는데 행이 가장 작은 블록, 이러한 블록이 여러 개라면 열이 작은 블록이 기준 블록이다.
 * 
 * 게임은 다음과 같이 실행된다.
 * 1. 크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
 * 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
 * 3. 격자에 중력이 작용한다.
 * 4. 격자가 90도 반시계 방향으로 회전한다.
 * 5. 다시 격자에 중력이 작용한다.
 * 
 * 게임은 제거한 블록의 개수^2 만큼 점수를 얻을 수 있다.
 * 게임이 종료된 후 얻게되는 점수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * 빡구현 bfs 문제
	 * 기준 블록 설명을 못찾아서 오래걸림.. (문제 좀 더 꼼꼼히)
	 * bfs에서 일반 블록을 무지개 블록으로 세서 계산이 자꾸 틀렸음.
	 * 
	 * bfs에서 무지개 블록도 visited true로 해주었는데 무지개는 탐색은 해도 visited에는 false로 되어있어야 함
	 * 그래서 bfsVisited를 만들어서 bfs에 필요한 visited로 사용, visited는 group을 찾는데 사용 (질문 게시판에서 봄)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		while (true) {
			Group selected = findBlockGroup();
			if (selected == null)
				break;
			remove(selected);
			int size = selected.members.size();
			answer += size * size;
			gravity();
			map = rotate();
			gravity();
		}
		System.out.println(answer);
	}

	public static Group findBlockGroup() {
		visited = new boolean[map.length][map[0].length];
		Group selectedGroup = null;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					Group group = bfs(new Point(i, j));
					if (group.members.size() < 2)
						continue;
					if (selectedGroup == null)
						selectedGroup = group;
					else if (group.compareTo(selectedGroup) < 0)
						selectedGroup = group;
				}
			}
		}
		return selectedGroup;
	}

	public static Group bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		ArrayList<Point> members = new ArrayList<>();
		boolean[][] bfsVisited = new boolean[map.length][map[0].length];
		q.add(start);
		members.add(start);
		bfsVisited[start.x][start.y] = true;
		visited[start.x][start.y] = true;
		int type = map[start.x][start.y];
		int rainbowCnt = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (bfsVisited[nextX][nextY])
						continue;
					if (map[nextX][nextY] == type || map[nextX][nextY] == 0) {
						if (map[nextX][nextY] == type)
							visited[nextX][nextY] = true;
						else
							rainbowCnt++;
						bfsVisited[nextX][nextY] = true;
						Point next = new Point(nextX, nextY);
						q.add(next);
						members.add(next);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		Point standard = members.get(0);
		for (Point compare : members) {
			if (map[compare.x][compare.y] == type)
				if (standard.compareTo(compare) > 0)
					compare = standard;
		}
		return new Group(rainbowCnt, members, standard);
	}

	public static void remove(Group group) {
		for (Point cur : group.members) {
			map[cur.x][cur.y] = -2;
		}
	}

	public static void gravity() {
		for (int j = 0; j < map[0].length; j++) {
			for (int i = map.length - 1; i >= 0; i--) {
				if (map[i][j] == -2) {
					for (int t = i - 1; t >= 0; t--) {
						if (map[t][j] >= 0) {
							int tmp = map[i][j];
							map[i][j] = map[t][j];
							map[t][j] = tmp;
							break;
						} else if (map[t][j] == -1)
							break;
					}
				}
			}
		}
	}

	public static int[][] rotate() {
		int[][] copyMap = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0, t = map.length - 1; j < map[0].length; j++, t--) {
				copyMap[t][i] = map[i][j];
			}
		}
		return copyMap;
	}
}

class Group implements Comparable<Group> {
	int rainbowCnt;
	ArrayList<Point> members;
	Point standard;

	public Group(int rainbowCnt, ArrayList<Point> members, Point standard) {
		this.rainbowCnt = rainbowCnt;
		members.sort(Comparator.naturalOrder());
		this.members = members;
		this.standard = standard;
	}

	@Override
	public int compareTo(Group o) {
		if (members.size() > o.members.size())
			return -1;
		else if (members.size() == o.members.size())
			if (rainbowCnt > o.rainbowCnt)
				return -1;
			else if (rainbowCnt == o.rainbowCnt)
				return -(standard.compareTo(o.standard));
		return 1;
	}
}

class Point implements Comparable<Point> {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (x < o.x)
			return -1;
		else if (x == o.x)
			return y - o.y;
		return 1;
	}
}