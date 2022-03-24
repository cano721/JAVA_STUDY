package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
N×N 크기의 공간에 물고기 M마리와 아기 상어 1마리가 있다. 공간은 1×1 크기의 정사각형 칸으로 나누어져 있다. 한 칸에는 물고기가 최대 1마리 존재한다.

아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다. 가장 처음에 아기 상어의 크기는 2이고, 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.

아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다. 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.

아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.

더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다고 가정한다. 즉, 아기 상어가 먹을 수 있는 물고기가 있는 칸으로 이동했다면, 이동과 동시에 물고기를 먹는다. 물고기를 먹으면, 그 칸은 빈 칸이 된다.

아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이 된다.

공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
 * */
class Position{
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class d220324_아기상어 {
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n+1][n+1];
        int x = 0, y = 0;		//아기상어 위치
        int size = 2; 			//아기상어 크기
        
        ArrayList<Position>[] list = new ArrayList[6]; 
        for (int i = 0; i < 6; i++) {
			list[i] = new ArrayList<Position>();
		}
        
        for (int i = 1; i <= n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					x = i;
					y = j;
				}else if(map[i][j] > 0) {
					list[map[i][j]].add(new Position(i, j));
				}
			}
		}
        int[][] dp = new int[n+1][n+1];
        dp[x][y] = 1;
        for (int i = 0; i < 6; i++) {
			while(!list[i].isEmpty()) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < list[i].size(); j++) {
					int distance = dfs(x, y, list[i].get(i), 0);
					min = Math.min(distance, min);
				}
				
			}
		}
        
	}
	private static int dfs(int x, int y, Position position, int cnt) {
		if(x == position.x && y == position.y) {
			return cnt;
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx > n || nx < 1 || ny > n || ny < 1) continue;
			answer = Math.min(bfs(nx, ny, position, cnt+1), answer);
		}
		return answer;
	}

}
