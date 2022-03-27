package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Map에 상어 M마리가 산다.
 * 각 상어는 번호가 부여되어 있다.
 * 상어는 매초 움직이는데, 특별한 우선순위에 따라 움직인다.
 * 상어가 바라보고 있는 방향에 따라 어느 지역을 먼저 탐색할지가 달라진다.
 * 우선순위는 모든 상어가 다르다.
 * 상어가 이동한 지역에는 상어의 냄새가 밴다.
 * 냄새는 상어가 K번 움직이면 사라진다.
 * 또한 상어는 우선 냄새가 배지 않은 곳을 먼저 이동하려고 한다.
 * 그런 위치가 없다면 자신의 냄새가 있는 칸으로 이동한다.
 * 같은 공간에 상어가 여러 마리라면 번호가 가장 작은 상어를 제외한 나머지 모든 상어가 Map에서 사라진다.
 * Map에 1번 상어만 남기 위해 필요한 시간을 구하는 문제
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
	 * 구현 자체는 똑같이 구현한다.
	 * 냄새를 어떻게 처리할지에 대해 고민을 했다.
	 * TreeSet을 생각했는데 TreeSet은 hashCode와 equals를 사용하여 같은지 보지 않고 compareTo를 사용하여 같은지 비교한다. (결국 큐로 돌아옴)
	 * 
	 * 구현은 잘 됐는데 어딘가 계속 틀림.
	 * 결국 못찾고 반례보고 냄새가 안지워진다는 걸 알게됨
	 * removeCollisionShark의 else에서 상어가 자기 냄새쪽으로 움직일 때 traceMap만 바꿔줬더니 큐에 없어서 안지워줌.
	 * 탐색에는 활용하기 때문에 상어가 제대로 못움직임
	 * 자기 냄새쪽으로 움직인 것도 큐에 넣어주니 제대로 움직이면서 정답
	 * 한줄한줄 의미를 아직 완벽하게 이해하고 생각하면서 짜는 건 아닌 거 같음
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