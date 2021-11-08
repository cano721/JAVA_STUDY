package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n16197_두동전 {
	static int n, m;
	static char[][] arr;
	static int answer = -1;
	static int[][] coinPos = new int[2][2];
	static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new char[n][m];
		int temp = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = st.toString().charAt(j);
				if(arr[i][j] == 'o') {
					coinPos[temp][0] = i;
					coinPos[temp][1] = j;
					temp++;
				}
			}
		}
		
		solution();
		System.out.println(answer);
	}

	private static void solution() {
		
		int x1 = coinPos[0][0], y1 = coinPos[0][1], x2 = coinPos[1][0], y2 = coinPos[1][1];
		for (int i = 0; i < 4; i++) {
			int newX1 = x1+dx[i];
			int newY1 = y1+dy[i];
			int newX2 = x2+dx[i];
			int newY2 = y2+dy[i];
			
			if(newX1 < 0 || newX1 >= n || newY1 < 0 || newY1 >= m) {		//첫번째 동전이 떨어짐
				if(newX2 < 0 || newX2 >= n || newY2 < 0 || newY2 >= m) {	//두번째 동전도 떨어짐
					continue;
				}else {														//두번째 동전 떨어지지 않음
					answer++;
					return;
				}
			}else if(newX2 < 0 || newX2 >= n || newY2 < 0 || newY2 >= m) {	//두번째 동전도 떨어짐
				if(newX1 < 0 || newX1 >= n || newY1 < 0 || newY1 >= m) {	//첫번째 동전도 떨어짐
					continue;
				}else { 													//첫번째 동전 떨어지지 않음
					answer++;
					return;
				}
			}else {															//두 동전 모두 떨어지지 않음
				if(arr[newX1][newY1] != '#') {	//첫번째 동전 이동, 벽 아닐 때
					coinPos[0][0] = newX1;
					coinPos[0][1] = newY1;
				}
				if(arr[newX2][newY2] != '#') {	//두번째 동전 이동, 벽 아닐 때
					coinPos[1][0] = newX2;
					coinPos[1][1] = newY2;
				}
				answer++;
				
			}
		}
		
	}

}
