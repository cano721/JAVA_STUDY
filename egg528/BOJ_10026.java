package BOJ;

import java.io.*;
import java.util.*;

class Main{
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[][] one = new String[N][N];
		
		boolean[][] visitedOne = new boolean[N][N];
		boolean[][] visitedTwo = new boolean[N][N];
		
		
		// 일반 용, 적록색약 용 배열 생성
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split("");
			one[i] = temp;
		}
		

		int divisionOne = 0;
		int divisionTwo = 0;
		// 배열을 돌며 dfs
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visitedOne[i][j]) {
					divisionOne++;
					dfs(one, visitedOne, one[i][j], i, j);
				}
			}
		}
		
		// 적록색약 용 G를 R로 대체
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(one[i][j].equals("G")) {
					one[i][j] = "R";
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visitedTwo[i][j]) {
					divisionTwo++;
					dfs(one, visitedTwo, one[i][j], i, j);
				}
			}
		}
		
		
		System.out.println(divisionOne + " " + divisionTwo);
	}
		
		
	
	static void dfs(String[][] arr, boolean[][] visited, String color, int x, int y) {
		if(x < 0 || arr.length <= x || y < 0 || arr.length <= y) {
			return;
		}
		
		
		visited[x][y] = true;
		if(0 <= x-1 && arr[x-1][y].equals(color) && !visited[x-1][y]) {
			dfs(arr, visited, color, x-1, y);
		}
		
		if(x+1 < arr.length &&arr[x+1][y].equals(color) && !visited[x+1][y]) {
			dfs(arr, visited, color, x+1, y);
		}
		
		if(0 <= y-1 && arr[x][y-1].equals(color) && !visited[x][y-1]) {
			dfs(arr, visited, color, x, y-1);
		}
		
		if(y+1 < arr.length && arr[x][y+1].equals(color) && !visited[x][y+1]) {
			dfs(arr, visited, color, x, y+1);
		}
	}
}