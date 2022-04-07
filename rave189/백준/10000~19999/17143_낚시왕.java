package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * NxM ũ���� �����Ϳ��� ���ø� �Ϸ��� �Ѵ�.
 * ���ÿ��� �� ó�� 0�� �� ���ʿ� �ִ�.
 * 1. ���ÿ��� ���������� �� �� �̵��Ѵ�.
 * 2. �̵��� ������ ���ÿհ� ���� ����� �� ��´�.
 * 3. ��� ������ �����δ�.
 * ���� ���� ������ ���ÿ��� �ݴ��� ���� �������� �� ���� ����� ũ���� ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static PriorityQueue<Shark>[] sharks;
	static int[][] map;
	static int r, c;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * ������
	 * next �޼ҵ带 ����� ���� ���ؼ� ��Ǯ����.
	 * r, c�� �� ���� ������ ���Ϸ��� �ϴ� �ʹ� �������.
	 * ��, �� ������ �ƴ϶� �� �� ���� ���Ƽ� �ٽ� ���ڸ��� ���� 2*(r-1), 2*(c-1)�� �������� ������� ��.
	 * ��� �ӷ��� �ִ� 2*(r-1), 2*(c-1)�� �ΰ� Ž���ؾ� ��.
	 * ���� ��ġ�� �� ���� ã���� �ð� ���� �پ�� �� ���� �� ������.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		sharks = new PriorityQueue[c];
		for (int i = 0; i < c; i++)
			sharks[i] = new PriorityQueue<>((v1, v2) -> v1.x - v2.x);
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			sharks[y].add(new Shark(x, y, s, d, z));
		}
		int answer = 0;
		for (int cur = 0; cur < c; cur++) {
			if (!sharks[cur].isEmpty())
				answer += sharks[cur].poll().size;
			move();
		}
		System.out.println(answer);
	}

	public static void move() {
		Map<Integer, Shark> hash = new HashMap<>();
		map = new int[r][c];
		for (int i = 0; i < sharks.length; i++) {
			while (!sharks[i].isEmpty()) {
				Shark cur = sharks[i].poll();
				next(cur);
				if (cur.size > map[cur.x][cur.y]) {
					hash.remove(map[cur.x][cur.y]);
					map[cur.x][cur.y] = cur.size;
					hash.put(cur.size, cur);
				}
			}
		}
		for (int key : hash.keySet()) {
			Shark shark = hash.get(key);
			sharks[shark.y].add(shark);
		}
	}

	public static void next(Shark shark) {
		int length = shark.diretion <= 1 ? (r - 1) * 2 : (c - 1) * 2;
		int speed = shark.speed % length;
		for (int i = 0; i < speed; i++) {
			int nextX = shark.x + dx[shark.diretion];
			int nextY = shark.y + dy[shark.diretion];
			try {
				int check = map[nextX][nextY];
				shark.move(nextX, nextY);
			} catch (ArrayIndexOutOfBoundsException e) {
				shark.setOppositeDirection();
				i--;
			}
		}
	}
}

class Shark {
	int x, y, speed, diretion, size;

	public Shark(int x, int y, int speed, int diretion, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.diretion = diretion;
		this.size = size;
	}

	public void move(int nextX, int nextY) {
		x = nextX;
		y = nextY;
	}

	public void setOppositeDirection() {
		if (diretion == 0)
			diretion = 1;
		else if (diretion == 1)
			diretion = 0;
		else if (diretion == 2)
			diretion = 3;
		else
			diretion = 2;
	}

	@Override
	public String toString() {
		return "Shark [x=" + x + ", y=" + y + ", speed=" + speed + ", diretion=" + diretion + ", size=" + size + "]";
	}
}