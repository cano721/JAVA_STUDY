package 전체유형문제풀이.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [구현]
 * 
 * 1. 상어위치 초반 (0,0) 위치로 초기화
 * 2. 물고기 방향대로 이동시키기
 * 3. 상어 이동시키기 
 * 
 * */


public class BJ19236_청소년상어 {

	static int maxFish = 0;
    
    //반시계방형순서로 맞춰주기
    static int[] dx = {-1, -1,0,1,1,1,0,-1}; 
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		fishDir[][] map =  new fishDir[4][4];
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
					int f = Integer.parseInt(st.nextToken());
					int d = Integer.parseInt(st.nextToken())-1;
					
					map[i][j] = new fishDir(f, d, i, j, "f");
			}
		}
		
		//초기 상어위치 갱신
		fishDir shark = new fishDir(0,map[0][0].dir,0,0,"s");
		maxFish = map[0][0].fishNum;
		map[0][0] = shark;
		
		getMoveFish(map, shark, maxFish);
		
	    System.out.println(maxFish);
		
	}

	public static void getMoveFish(fishDir[][] fishMap, fishDir shark, int eatFishNo) {
		
		maxFish = Math.max(eatFishNo, maxFish);
		
		//1. 물고기 이동시키기
		moveFish(fishMap);
		
		//2.상어 이동시키기
		//상어는 1칸,2칸,3칸 중에 이동이 가능함
		for (int i = 1; i <4; i++) {
			int nx = shark.x + dx[shark.dir]*i;
			int ny = shark.y + dy[shark.dir]*i;
			
			//범위를 벗어나고, 물고기가 없다면 무시
			if(nx > 0 || nx <= 4 || ny > 0 || ny <=4 ) continue;
			if(fishMap[nx][ny] == null) continue;
			
			//이동가능한 위치로 상어 이동시키기
			int fishNum = fishMap[nx][ny].fishNum;
			fishDir sharkNew = new fishDir(0, fishMap[nx][ny].dir, nx, ny, "s");
			
			//맵 복사 이후에 상어 위치 넣기
			fishDir[][] copyMap = getCopyMap(fishMap);
			copyMap[nx][ny] = sharkNew;
			
			//상어가 지난간 곳은 null처리
			copyMap[shark.x][shark.y] = null;
			
			getMoveFish(copyMap, sharkNew, fishNum+eatFishNo);
		}
		
	}

	//기본 map정보 카피해서 저장하기
	public static fishDir[][] getCopyMap(fishDir[][] mapOrg) {
		fishDir[][] copy = new fishDir[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
			
				if(mapOrg[i][j] != null) copy[i][j] = new fishDir(mapOrg[i][j]);	
			}
		}
		
		return copy;
	}

	//물고기 이동시키기
	public static void moveFish(fishDir[][] fishMap) {
		//1번부터 순차적으로 이동
		for (int i = 1; i < 17; i++) {
			
			MM: for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
						
					if(fishMap[j][k] != null && fishMap[j][k].fishNum == i) {
						moveFishOne(fishMap[j][k], fishMap);
						break MM;
					}
				}
			}	
		}	
	}

	public static void moveFishOne(fishDir fish, fishDir[][] fishMap) {
		int dir = fish.dir;
		//반시계 방향으로 이동하면서 움직이기
		for (int d = dir; d < dir+dx.length; d++) {
			
			//물고기 방향 재설정
			int nd = d%8;
			fish.dir = nd;
			
			int nx = fish.x + dx[nd];
			int ny = fish.y + dy[nd];
			
			if(nx > 0 || nx <= 4 || ny > 0 || ny <=4 ) continue;
			
			//이동가능한 곳이면(빈칸) 이동하기
			if(fishMap[nx][ny] == null) {
				fishMap[fish.x][fish.y] = null;
				fish.x = nx;
				fish.y = ny;
				fishMap[nx][ny] = fish;
				return;
			}
			
			//물고기 자리면, 자리 바꾸기
			else if(fishMap[nx][ny].type.equals("f")) {
				fishDir swapFish = fishMap[nx][ny];
				swapFish.x = fish.x;
				swapFish.y = fish.y;
				
				fish.x = nx;
				fish.y = ny;
				
				fishMap[nx][ny] = fish;
				fishMap[swapFish.x][swapFish.y] = swapFish;
				
				return;
			}
			
		}
		
	}

	//번호, 방향, x, y, 타입(물고기, 상어)
	public static class fishDir{
		int fishNum;
		int dir;
		int x;
		int y;
		String type;
		
		public fishDir(int fishNum, int dir, int x, int y, String type) {
			this.fishNum = fishNum;
			this.dir = dir;
			this.x = x;
			this.y = y;
			this.type = type;
		}
		
		//map 정보 클론
		public fishDir(fishDir fd) {
			this.fishNum = fd.fishNum;
			this.dir = fd.dir;
			this.x = fd.x;
			this.y = fd.y;
			this.type = fd.type;
		}
	}
	
}
