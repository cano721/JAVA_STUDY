package dfsbfs;

import java.util.*;

public class BJ16173_점프왕쩰리 {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static boolean res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 n = sc.nextInt();
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++){
			for(int j=0; j<n; j++){
				map[i][j] =sc.nextInt();
			}
		}
		
		getSuccess(0,0);
		if(res) System.out.print("HaruHaru");
		else System.out.print("Hing");

	}

	private static void getSuccess(int i, int j){
		
		if(i < 0 || i>=n || j < 0 || j >= n){
			return;
		}
		
		else{
			if(i == n-1 && j == n-1){res = true; return;}
		}
		
		if(!visited[i][j]){
		
			visited[i][j] = true;
			getSuccess(i+map[i][j],j);
			getSuccess(i, j+map[i][j]);
			visited[i][j] = false;
		
		}
	
	}
	
}
