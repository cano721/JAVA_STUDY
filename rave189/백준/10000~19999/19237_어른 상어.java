package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Map�� ��� M������ ���.
 * �� ���� ��ȣ�� �ο��Ǿ� �ִ�.
 * ���� ���� �����̴µ�, Ư���� �켱������ ���� �����δ�.
 * �� �ٶ󺸰� �ִ� ���⿡ ���� ��� ������ ���� Ž�������� �޶�����.
 * �켱������ ��� �� �ٸ���.
 * �� �̵��� �������� ����� ������ ���.
 * ������ �� K�� �����̸� �������.
 * ���� ���� �켱 ������ ���� ���� ���� ���� �̵��Ϸ��� �Ѵ�.
 * �׷� ��ġ�� ���ٸ� �ڽ��� ������ �ִ� ĭ���� �̵��Ѵ�.
 * ���� ������ �� ���� ������� ��ȣ�� ���� ���� �� ������ ������ ��� �� Map���� �������.
 * Map�� 1�� �� ���� ���� �ʿ��� �ð��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static final int DIRECTION_SIZE = 4;
	static int[][] map, traceMap, printMap;
	static Shark[] sharks;
	static Queue<Smell> smellTrace = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	/**
	 * ���� ��ü�� �Ȱ��� �����Ѵ�.
	 * ������ ��� ó�������� ���� ����� �ߴ�.
	 * TreeSet�� �����ߴµ� TreeSet�� hashCode�� equals�� ����Ͽ� ������ ���� �ʰ� compareTo�� ����Ͽ� ������ ���Ѵ�. (�ᱹ ť�� ���ƿ�)
	 * 
	 * ������ �� �ƴµ� ��� ��� Ʋ��.
	 * �ᱹ ��ã�� �ݷʺ��� ������ ���������ٴ� �� �˰Ե�
	 * removeCollisionShark�� else���� �� �ڱ� ���������� ������ �� traceMap�� �ٲ������ ť�� ��� ��������.
	 * Ž������ Ȱ���ϱ� ������ �� ����� ��������
	 * �ڱ� ���������� ������ �͵� ť�� �־��ִ� ����� �����̸鼭 ����
	 * �������� �ǹ̸� ���� �Ϻ��ϰ� �����ϰ� �����ϸ鼭 ¥�� �� �ƴ� �� ����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		printMap = new int[n][n];
		map = new int[n][n];
		traceMap = new int[n][n];
		sharks = new Shark[m + 1];
		for (int i = 0; i < n; i++) {
			Arrays.fill(traceMap[i], -1);
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					sharks[map[i][j]] = new Shark(i, j);
					smellTrace.add(new Smell(i, j, 0));
					traceMap[i][j] = 0;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < sharks.length; i++) {
			int direction = Integer.parseInt(st.nextToken()) - 1;
			sharks[i].setDirection(direction);
		}
		for (int i = 1; i < sharks.length; i++) {
			int[][] priority = new int[DIRECTION_SIZE][DIRECTION_SIZE];
			for (int j = 0; j < priority.length; j++) {
				st = new StringTokenizer(br.readLine());
				for (int t = 0; t < priority.length; t++)
					priority[j][t] = Integer.parseInt(st.nextToken()) - 1;
			}
			sharks[i].setPriority(priority);
		}
		int time = 0;
		while (m > 1 && ++time <= 1000) {
			move();
			m -= removeCollisionShark(time);
			removeSmell(time - k);
		}
		System.out.println(time == 1001 ? -1 : time);
	}

	public static void move() {
		next: for (int sharkNo = 1; sharkNo < sharks.length; sharkNo++) {
			Shark shark = sharks[sharkNo];
			if (shark == null)
				continue;
			printMap[shark.x][shark.y] = 0;
			for (int j = 0; j < DIRECTION_SIZE; j++) {
				int direction = shark.priority[shark.direction][j];
				int nextX = shark.x + dx[direction];
				int nextY = shark.y + dy[direction];
				try {
					if (map[nextX][nextY] != 0)
						continue;
					shark.move(nextX, nextY, direction);
					printMap[nextX][nextY] = sharkNo;
					continue next;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			for (int j = 0; j < DIRECTION_SIZE; j++) {
				int direction = shark.priority[shark.direction][j];
				int nextX = shark.x + dx[direction];
				int nextY = shark.y + dy[direction];
				try {
					if (map[nextX][nextY] == sharkNo) {
						shark.move(nextX, nextY, direction);
						printMap[nextX][nextY] = sharkNo;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}

	public static int removeCollisionShark(int curTime) {
		int removeCnt = 0;
		for (int sharkNo = 1; sharkNo < sharks.length; sharkNo++) {
			Shark shark = sharks[sharkNo];
			if (shark == null)
				continue;
			int curShark = map[shark.x][shark.y];
			if (curShark == 0) {
				map[shark.x][shark.y] = sharkNo;
				smellTrace.add(new Smell(shark.x, shark.y, curTime));
				traceMap[shark.x][shark.y] = curTime;
			} else if (curShark < sharkNo) {
				sharks[sharkNo] = null;
				removeCnt++;
			} else {
				smellTrace.add(new Smell(shark.x, shark.y, curTime));
				traceMap[shark.x][shark.y] = curTime;
			}
		}
		return removeCnt;
	}

	public static void removeSmell(int limit) {
		while (!smellTrace.isEmpty() && smellTrace.peek().time <= limit) {
			Smell p = smellTrace.poll();
			if (traceMap[p.x][p.y] == p.time) {
				map[p.x][p.y] = 0;
				traceMap[p.x][p.y] = -1;
			}
		}
	}

	public static void print(int[][] map) {
		for (int[] row : map) {
			for (int cell : row)
				System.out.print(cell + " ");
			System.out.println();
		}
		System.out.println();
	}
}

class Shark {
	int x, y, direction;
	int[][] priority;

	public Shark(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPriority(int[][] priority) {
		this.priority = priority;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void move(int nextX, int nextY, int direction) {
		x = nextX;
		y = nextY;
		this.direction = direction;
	}
}

class Smell {
	int x, y, time;

	public Smell(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}