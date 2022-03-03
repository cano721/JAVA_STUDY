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
	 * 좌표평면 상에 (0, 0) 지점에 로봇이 있다.
	 * 좌표평면은 가로 세로가 (-5, 5)까지로 이루어져 있다.
	 * 명령어가 주어지면 명령어에 따라 로봇이 움직인다.
	 * 로봇은 한 번 갔던 길을 또 지나갈 수 있다.
	 * 이 때, 로봇이 처음 걸어본 길의 길이를 구하는 문제
	 * 
	 * 현재 위치에서 위로 올라갔다면 [현재위치][위] = true이고, [다음위치][아래]도 true로 바꾸어 주어야 한다.
	 * @param dirs 명령어들
	 * @return 처음 걸어본 길의 길이
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