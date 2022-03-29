package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxN ���ڰ� �ִ�.
 * �� ���ڿ��� ����� �ϳ����ְ�, ����� ������, ������, �Ϲ� ����� �ִ�.
 * �Ϲ� ����� M���� ���� �ְ�, M������ �ڿ����� ǥ���ȴ�.
 * �������� -1, �������� 0���� ǥ���ȴ�.
 * ��ϱ׷��� �����¿�� ������ ����� �����̴�.
 * �� �� �׷쿡�� �Ϲ� ����� ��� �ϳ� �־�� �ϸ�, �Ϲ� ����� ���� ��� ���ƾ� �Ѵ�.
 * ����, ������ ����� ����� �ϰ�, ������ ����� ���� ������ ��� ����.
 * �׸��� �׷��� ũ��� 2 �̻��̾�� �Ѵ�.
 * �׷쿡�� ���� ����� �ִµ� ���� ���� ���� ���, �̷��� ����� ���� ����� ���� ���� ����� ���� ����̴�.
 * 
 * ������ ������ ���� ����ȴ�.
 * 1. ũ�Ⱑ ���� ū ��� �׷��� ã�´�. �׷��� ��� �׷��� ���� ����� ���Ե� ������ ����� ���� ���� ���� ��� �׷�, �׷��� ��ϵ� ��������� ���� ����� ���� ���� ū ����, �� �͵� �������̸� ���� ���� ū ���� ã�´�.
 * 2. 1���� ã�� ��� �׷��� ��� ����� �����Ѵ�. ��� �׷쿡 ���Ե� ����� ���� B��� ���� ��, B2���� ȹ���Ѵ�.
 * 3. ���ڿ� �߷��� �ۿ��Ѵ�.
 * 4. ���ڰ� 90�� �ݽð� �������� ȸ���Ѵ�.
 * 5. �ٽ� ���ڿ� �߷��� �ۿ��Ѵ�.
 * 
 * ������ ������ ����� ����^2 ��ŭ ������ ���� �� �ִ�.
 * ������ ����� �� ��ԵǴ� ������ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * ������ bfs ����
	 * ���� ��� ������ ��ã�Ƽ� �����ɸ�.. (���� �� �� �Ĳ���)
	 * bfs���� �Ϲ� ����� ������ ������� ���� ����� �ڲ� Ʋ����.
	 * 
	 * bfs���� ������ ��ϵ� visited true�� ���־��µ� �������� Ž���� �ص� visited���� false�� �Ǿ��־�� ��
	 * �׷��� bfsVisited�� ���� bfs�� �ʿ��� visited�� ���, visited�� group�� ã�µ� ��� (���� �Խ��ǿ��� ��)
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