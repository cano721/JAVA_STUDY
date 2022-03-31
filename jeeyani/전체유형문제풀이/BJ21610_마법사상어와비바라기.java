package 전체유형문제풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * [구현]
 * 
 * 0. 첫번째 구름생성 위치 (N, 1), (N, 2), (N-1, 1), (N-1, 2)
 * 
 * 1. 모든 구름이 di 방향으로 si칸 이동한다.
 * 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 * 3. 구름이 모두 사라진다. ==> "구름이었던 칸 기억하쟈"
 * 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
 * 			- 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 * 			- 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 * 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
 * 
 * */


public class BJ21610_마법사상어와비바라기 {

	static int n,m,ans=0;
	static int[][] map;
	static List<Move> moveList = new ArrayList<>();
	static Queue<Pos> cloud = new LinkedList<>();
	static boolean [][] visited;
	

    //반시계방형순서로 맞춰주기
    static int[] dx = {-1,-1, 0, 1,1,1,0,-1}; 
    static int[] dy = {0,-1, -1,-1,0,1,1,1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n  = Integer.parseInt(st.nextToken());
		m  = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		visited = new boolean[n+1][n+1];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				
				map[i][j] =Integer.parseInt(st.nextToken());
				
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			moveList.add(new Move(d, s));

		}
		
		//첫번째는 무조건 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 구름 생성
		cloud.add(new Pos(n-1,1));
		cloud.add(new Pos(n-1,2));
		cloud.add(new Pos(n-2,1));
		cloud.add(new Pos(n-2,2));
		
		for (int i = 0; i < m; i++) {
			int d = moveList.get(i).d;
			int s = moveList.get(i).s;
			
			
			getCloudMove(d,s);
		}
		
	    System.out.println(ans);
		
	}
	
	//구름 이동하기
	private static void getCloudMove(int d, int s) {
		
		Queue<Pos> cloudPos = new LinkedList<>();
		
		
		while(!cloud.isEmpty()) {
			
			Pos cloudNode = cloud.poll();
			
			int x = cloudNode.x;
			int y = cloudNode.y;
			
			int nx = x + (dx[d-1]*s);
			int ny = y + (dy[d-1]*s);
			
			//구름이동시 연결된 상황에 맞춰 이동
			while(!isCheck(nx)) {
				nx = getChange(nx);
			}
			while(!isCheck(ny)) {
				ny = getChange(ny);
			}
			
			cloudPos.add(new Pos(nx, ny));
			map[nx][ny]++;
			visited[nx][ny] = true;
			
		}
		
		while (!cloudPos.isEmpty()) {
			
			Pos node = cloudPos.poll();
			
			int basketCnt = 0; //바구니의 수
			
			//물복사버그 마법을 사용하면, 
			//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가
			for (int i = 0; i < 8; i+=2) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if(!isCheck(nx) || !isCheck(ny) || map[nx][ny] < 1) continue;
				basketCnt++;
			}
			map[node.x][node.y] += basketCnt;
		}
		cloudMake();
	}

	private static void cloudMake() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
				if(map[i][j] >= 2 && !visited[i][j]) {
					cloud.add(new Pos(i, j));
					map[i][j] -=2;
				}
				else {
					visited[i][j] = false;
				}
				ans += map[i][j];
			}
		}
		
	}

	//구름이동
	/*
	 * 0 보다 내려가면 +N
	 * N 보다 올라가면 -N
	 * 
	 * */
	private static int getChange(int x) {
		return x < 1 ? x + n : x - n;
	}

	//범위확인
	private static boolean isCheck(int x) {
		return x > 0 && x <= n;
	}

	public static class Move{
		int d;
		int s;
		
		public Move(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	
	public static class Pos{
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
