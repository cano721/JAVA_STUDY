package backtracking;

import java.util.*;

public class BJ15650_NandM_2 {

	static int n;
	static int m;
	static int[] result;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		
		//결과를 담을 배열
		result = new int[m];

		check(1,0);
		
	}

	private static void check(int start, int loc) {
		
		/*
		 * 종단조건 설정
		 */
		//m 갯수에 맞게 배열을 모두 채웠으면 출력
		if(loc == m) {
			for(int temp : result) {
				System.out.print(temp+" ");
			}
			System.out.println();
			return;
		}
		
		//시작값을 정해줌으로써 visited처리를 확인하지 않아도 가능!
		for (int i = start; i <= n; i++) {
			
			result[loc] = i;
			check(i+1, loc+1);
		}
		
		
	}

}
