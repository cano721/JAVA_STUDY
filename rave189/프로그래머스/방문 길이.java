package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] map = new int[11][11];
	boolean[][][] visited = new boolean[map.length][map[0].length][4];
	int[] dx = { 1, -1, 0, 0 };
	int[] dy = { 0, 0, 1, -1 };

	/**
	 * ��ǥ��� �� (0, 0) ������ �κ��� �ִ�.
	 * ��ǥ����� ���� ���ΰ� (-5, 5)������ �̷���� �ִ�.
	 * ��ɾ �־����� ��ɾ ���� �κ��� �����δ�.
	 * �κ��� �� �� ���� ���� �� ������ �� �ִ�.
	 * �� ��, �κ��� ó�� �ɾ ���� ���̸� ���ϴ� ����
	 * 
	 * ���� ��ġ���� ���� �ö󰬴ٸ� [������ġ][��] = true�̰�, [������ġ][�Ʒ�]�� true�� �ٲپ� �־�� �Ѵ�.
	 * @param dirs ��ɾ��
	 * @return ó�� �ɾ ���� ����
	 */
	public int solution(String dirs) {
		int answer = 0;
		Point cur = new Point(5, 5);
		for (char ch : dirs.toCharArray()) {
			int direction = getDirection(ch);
			int oppositeDirection = getOppositePoint(direction);
			int nextX = cur.x + dx[direction];
			int nextY = cur.y + dy[direction];
			try {
				if (!visited[cur.x][cur.y][direction] && !visited[nextX][nextY][oppositeDirection])
					answer++;
				visited[cur.x][cur.y][direction] = true;
				visited[nextX][nextY][oppositeDirection] = true;
				cur.update(nextX, nextY);
			} catch (Exception e) {
			}
		}
		return answer;
	}

	public int getDirection(char ch) {
		if (ch == 'U')
			return 1;
		else if (ch == 'D')
			return 0;
		else if (ch == 'R')
			return 2;
		else
			return 3;
	}

	public int getOppositePoint(int direction) {
		if (direction % 2 == 0)
			return direction + 1;
		else
			return direction - 1;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
}