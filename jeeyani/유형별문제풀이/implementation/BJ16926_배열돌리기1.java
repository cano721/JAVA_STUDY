package 유형별문제풀이.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * (1,1) (2,2),(3,3)..단위씩 돌아감
 * 
 * 3*3배열은 1개/ 4*4배열과 5*5배열 2개/6*6배열 3개 그룹 단위를 돌림 ==> min(m,n)/2
 * 
 * 따라서 범위내에서 우상좌하 방향으로 돌려주기
 * 
 */

public class BJ16926_배열돌리기1 {

	static int n,m,r,A[][];
	
	//우상좌하
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n= Integer.parseInt(st.nextToken());
		m= Integer.parseInt(st.nextToken());
		r= Integer.parseInt(st.nextToken());
		
		A = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());;
			}
		}
		
		//돌려야하는 그룹의 갯수
		int groupCnt = Math.min(n, m)/2;
		
		//오답
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < groupCnt; j++) {
				int x = j;
				int y = j;
				
				int temp = A[x][y];
				int indx = 0;
				
				while(indx < 4) {
					int nx = x + dx[indx];
					int ny = y + dy[indx];
					
					//범위내 있을 경우
					if(nx >= j && ny >= j && nx < n-j && ny < m-j) {
						A[x][y] = A[nx][ny];
						x = nx;
						y = ny;
					}
					else indx++;
					
				}
				A[j+1][j] = temp;
			}
		}
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(A[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
