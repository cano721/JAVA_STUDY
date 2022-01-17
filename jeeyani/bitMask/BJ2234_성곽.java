package bitMask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.swing.internal.plaf.basic.resources.basic;

/*
 * 
 * 1. 각 성벽의 값은 1,2,4,8 임으로 비트마스크 가능
 * 
 * 	rampart[i][j] & 1 ==0001 이면 서쪽
 * 	rampart[i][j] & 2 ==0010 이면 북
 * 	rampart[i][j] & 3 ==0100 이면 동
 * 	rampart[i][j] & 4 ==1000 이면 남
 * 
 * 2. 방의 갯수와 가장 넓은 방의 크기는 bfs 사용하여 탐색
 * 3. 벽을 하나씩 허물면서 방의 크기를 기억하며 최종적으로 가장 넓은 방의 크기를 구함
 * 
 * 
 * 
 @author Jeeyani
 */

public class BJ2234_성곽 {

	static int n, m;
	static int[][] rampart;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static class rampartNode {

		private int x;
		private int y;

		public rampartNode(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		rampart = new int[m][n];
		visited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				rampart[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		//1.각 방의 갯수와 2. 넓은 방의 크기 구하기

		int roomCnt = 0;
		int MaxRoom = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					int roomSize = getBFS(i, j);

					MaxRoom = Math.max(MaxRoom, roomSize);
					roomCnt++;
				}
			}

		}
		
		//3.벽을 제거하여 얻을 수 있는 넓은 방 크기 구하기
		int maxRoomDel = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 1; k <=8 ; k*=2) {
					
					if((rampart[i][j] & k) != 0) {
						visited = new boolean[m][n];
						rampart[i][j] -=k;
						maxRoomDel = Math.max(maxRoomDel, getBFS(i, j));
						rampart[i][j] +=k;
						
					}
					
				}
			}

		}

		StringBuffer sb = new StringBuffer();
		sb.append(roomCnt+"\n"+MaxRoom+"\n"+maxRoomDel);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static int getBFS(int x, int y) {

		int size = 1;

		Queue<rampartNode> q = new LinkedList<>();
		q.offer(new rampartNode(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			rampartNode node = q.poll();
			x = node.getX();
			y = node.getY();

			int wall = 1;

			for (int i = 0; i < 4; i++) {

				//해당 방향에 벽이 없는 경우
				if ((rampart[x][y] & wall) != wall) {

					int nx = x + dx[i];
					int ny = y + dy[i];
					//공간을 벗어난 경우 무시
					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;
					
					if (!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new rampartNode(nx, ny));
						size++;
					}
				}
				wall *=2;
			}
		}

		return size;
	}

}
