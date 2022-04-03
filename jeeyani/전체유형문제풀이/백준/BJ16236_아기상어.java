package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [bfs, 구현]
 * 
 * 
 * 1. bfs를 이용하여 가장 가까운 물고기의 위치를 찾기
 * 
 * 2. 물고기를 못먹는 경우 생각하기 
 * 2-1. 물고기의 크기
 * 2-2. 거리비교하기 (같을 경우, 1: 가장 위 , 2: 가장 왼)
 * 
 * 3. 물고기를 먹는 경우
 * 	이동횟수 계산, 물고기 먹은 갯수에 따른 물고기 크기를 증가시키기
 * 
 * 4. 더 이상 먹을 수 있는 물고기가 없는 경우, 이동횟수를 반환
 * 
 * 
 * */


public class BJ16236_아기상어 {

	static final int INF = Integer.MAX_VALUE;
	static int n;
    static int[][] map; 
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		
		int shX = 0;
		int shY = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j]==9) {
					shX = i;
					shY= j;
					map[i][j] = 0; //상어의 시작 위치 0으로 변경
				}
			}
		}
		
		 int result = getClosedFish(shX, shY);
	     System.out.println(result);
		
	}

	public static int getClosedFish(int shX, int shY) {
		int moveCnt = 0;
		//상어 초기 사이즈, 물고기 먹은 횟수
		int size = 2;
		int fishCnt = 2;
		
		//현재의 상어위치로 초기화
		fish minFish = new fish(shX, shY, 0);
		
		do {
			
			boolean[][] visited = new boolean[n][n];
			Queue<fish> q = new LinkedList<>();
			visited[minFish.x][minFish.y] = true;
			
			q.add(new fish(minFish.x, minFish.y, 0));
			minFish.dist = INF;
			
			while(!q.isEmpty()) {
				fish f = q.poll();
				
				
				//더 이상 확인할 물고기가 없다면 종료
				if(f.dist > minFish.dist) break;
				
				/* 물고기를 못 먹는 경우 체크하기*/
				// 현재 상어보다 큰 경우 무시
				if(map[f.x][f.y] > size) continue;
				
				//빈칸이 아니고 현재 상어보다 사이즈가 작은 경우 ==> 각 물고기들의 거리를 체크하기
				if(map[f.x][f.y] != 0 && map[f.x][f.y] < size) {
					
					//만약 현재까지의 최단거리보다 가까우면 물고기 위치 값 갱신
					if(f.dist < minFish.dist) {
						minFish = f;
					}
					
					//거리가 같은 경우
					else if (f.dist == minFish.dist) {
						//가장 위에 있는 물고기로 변경
						if(f.x < minFish.x) {
							minFish = f;
						}
						//가장 위에 물고기가 여러마리인 경우, 가장 왼쪽 물고기로 변경
						else if(f.x == minFish.x && f.y < minFish.y) {
							minFish = f;
						}
					}
					continue;
				}
				
				//이동하면서 현재 위치에서 부터 먹을 수 있는 물고기까지의 거리 구하기
				for (int i = 0; i < 4; i++) {
					int nx = f.x + dx[i];
					int ny = f.y + dy[i];
					
					//범위를 벗어나거나, 방문했던 곳은 무시
					if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					if(visited[nx][ny]) continue;
					
					visited[nx][ny] = true;
					q.add(new fish(nx, ny, f.dist+1));
				}
			}
			
			//물고기를 찾은 경우 (먹을 수 있는 경우)
			if(minFish.dist != INF) {
				moveCnt += minFish.dist;
				fishCnt--;
				
				if(fishCnt == 0 ) {
					size++;
					fishCnt = size;
				}
				map[minFish.x][minFish.y] = 0;
			}
			
		} while (minFish.dist != INF);
		
		
		
		return moveCnt;
	}
	
	

	public static class fish{
		int x;
		int y;
		int dist;
		
		public fish(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
}
