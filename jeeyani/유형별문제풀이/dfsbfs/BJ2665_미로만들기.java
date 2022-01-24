package 유형별문제풀이.dfsbfs;

import java.util.*;

/*
 * 
 * BFS와 다익스트라 알고리즘 활용 문제
 * 
 * 다익스트라 알고리즘은 https://blog.naver.com/ndb796/221234424646 참조하여 공부
 * 
 * "현재까지 알고 있던 최단 경로를 계속해서 갱신" 
 * 
 * int 형의 visited배열 선언 : 각 위치마다 방을 바꾼 횟수를 저장
 *  		- visited 무한대로 선언, 이후 해당 위치에 최소값을 저장해 나감
 *  
 *  벽을 만날 경우, 해당 벽을 기준으로 visited배열의 위치값을 +1증가시키며 탐색 진행...
 * 
 * 
 */

public class BJ2665_미로만들기 {

	static class Block{
		int x;
		int y;
		
		Block(int y, int x){
			this.x = x;
			this.y = y;
		}
	}
	
	static int n;
	static int[][] board;
	static int[][] vistied;
	
	static int[] dy= {-1,1,0,0};
	static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		board = new int[n][n];
		vistied = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String temp = sc.next();
			for (int j = 0; j < n; j++) {
				board[i][j] = temp.charAt(j)-'0';
				vistied[i][j] = Integer.MAX_VALUE;
			}
		}
		
		getRouteBFS(0,0);
		
		System.out.print(vistied[n-1][n-1]);
	}

	private static void getRouteBFS(int y, int x) {
		Queue<Block> q = new LinkedList<>();
		q.add(new Block(y, x));
		vistied[0][0] = 0;
		
		while(!q.isEmpty()) {
			
			Block block = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = block.y + dy[i];
				int nx = block.x + dx[i];
				
				//범위 벗어나는 경우 무시
				if(ny < 0 || nx < 0 || ny >=n || nx >=n) continue;
				
				//이동하기 전의 값보다 이동한 후의 값에 더 작은 값이 들어있다면, 최소값을 유지
				if(vistied[ny][nx] <= vistied[block.y][block.x]) continue;
				
				//이동할 수 있는 방인 경우
				if(board[ny][nx] ==1) {
					q.add(new Block(ny,nx));
					vistied[ny][nx] = vistied[block.y][block.x];
				}
				//이동할 수 없는 방인 경우, 방을 바꾼 횟수로 +1 증가 시키기
				else {
					q.add(new Block(ny,nx));
					vistied[ny][nx] = vistied[block.y][block.x]+1;
				}
				
			}
			
		}
		
	}

}
