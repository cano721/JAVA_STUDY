package 유형별문제풀이.dfsbfs;

import java.io.*;
import java.util.*;

class cheeseNode{
	int x;
	int y;

	cheeseNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BJ2638_치즈 {

	static int n;
	static int m;
	static int[][] board;
	static boolean[][] visited;
	static int time;
	static ArrayList<cheeseNode> cheeseList;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int cheeseCnt=0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		//visited = new boolean[n][m];
		
		cheeseList = new ArrayList<cheeseNode>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
				if(board[i][j]==1) { 
					cheeseList.add(new cheeseNode(i, j));
					cheeseCnt++;
				}
			}
		}
		
		//더 이상 녹일 치즈가 없다면 종료
		while(cheeseCnt !=0) {
			time++;
			visited = new boolean[n][m]; //녹인 이후마다 방문 기록 초기화!
			airCHK(0,0);
			melting();
		}
		
		System.out.println(time);
	}

	
	//공기와 접촉한 치즈인지 확인하기
	private static void airCHK(int i, int j) {
		visited[i][j] = true;
		board[i][j] = 2;
		
		for (int k = 0; k < 4; k++) {
			int x = i+dx[k];
			int y = j+dy[k];
			
			//범위 벗어날 경우 넘어가기
			if(x < 0 || x >= n || y < 0 || y >= m){
				continue;
			}
			//외부공기와 접촉하지 않은 치즈
			if(visited[x][y] || board[x][y] == 1) {
				continue;
			}
			
			airCHK(x,y);
			
		}
		
		
	}

	//치즈 녹이기
	private static void melting() {
		for (int i = 0; i < cheeseList.size(); i++) {
			int x = cheeseList.get(i).x;
			int y = cheeseList.get(i).y;
			
			int cnt = 0;
			
			for(int j = 0; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];
				
				//공기일 경우 녹는 치즈의 갯수 계산
				if(board[nx][ny] == 2) {
					cnt++;
				}
			}
			//공기와 접촉하는 변이 2개 이상인 경우 해당 치즈녹이기
			if(cnt >=2) {
				board[x][y] = 0;
				cheeseCnt--;
				cheeseList.remove(i);
				i--;
			}
		}
		
		
	}

}
