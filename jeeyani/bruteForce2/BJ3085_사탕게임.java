package bruteForce2;

import java.util.Scanner;

public class BJ3085_사탕게임 {

	static int n;
	static char[][] candy;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		candy = new char[n][n];

		//2차 행렬로 입력받기
		for (int i = 0; i < n; i++) {
			String temp = sc.next();
			for (int j = 0; j < n; j++) {
				candy[i][j] = temp.charAt(j);
			}
		}

		int comvalue = 0;
		//우측방향으로 색상 다를 시 변경
		for (int i = 0; i < n-1; i++) { 
			for (int j = 0; j < n; j++) {
				if(candy[i][j] != candy[i+1][j]) {
					char temp = candy[i][j];
					candy[i][j] = candy[i+1][j];
					candy[i+1][j] = temp;
					
					comvalue = getCount(candy);
					
					temp = candy[i+1][j];
					candy[i+1][j] = candy[i][j];
					candy[i][j] = temp;
				}
			}
		}
		
		//아래방향으로 색상 다를 시 변경
		for (int i = 0; i < n; i++) { 
			for (int j = 0; j < n-1; j++) {
				if(candy[i][j] != candy[i][j+1]) {
					char temp = candy[i][j];
					candy[i][j] = candy[i][j+1];
					candy[i][j+1] = temp;
					
					comvalue = getCount(candy);
					
					temp = candy[i][j+1];
					candy[i][j+1] = candy[i][j];
					candy[i][j] = temp;
				}
				
			}
		}
		System.out.println(comvalue);

	}

	//행 또는 열방향의 같은 색상 캔디 갯수 확인
	private static int getCount(char[][] swapCandy) {
		int ans = 1;
		for (int i = 0; i < n; i++) {

			int rowCnt = 1;
			int colCnt = 1;
			
			for (int j = 0; j < n-1; j++) {

				//행 체크
				if (swapCandy[i][j] == swapCandy[i][j + 1])
					colCnt++;
				else
					colCnt = 1;
				ans = Math.max(ans, colCnt);

				//열 체크
				if (swapCandy[j][i] == swapCandy[j + 1][i])
					rowCnt++;
				else
					rowCnt = 1;
				ans = Math.max(ans, rowCnt);
			}
		}

		return ans;
	}

}
