package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2231_분해합 {

	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		
		int i =0;
		for (i = 0; i < n; i++) {
			
			int constr = i;
			int temp = i;
			
			while (temp > 0) {
				
				constr +=temp%10;
				temp /=10;
				
			}
			
			if(constr == n) {
				System.out.println(i);
				break;
			}
			
		}
		//생성자가 없을 경우!
		if(i==n) System.out.println(0);
		
		
		/*
		 * (21.10.26) 오류
		 */
		/*
		//1.자릿수 확인하기
		int num = 0;
		int input = n;
		while(input !=0) {
			input = input/10;
			num++;
		}
		
		//2.생성자가 가질 수 있는 가장 큰수
		int constructorMax = n - num*9; //생성자가 가질 수 있는 가장 큰 수
		
		//3.생성자 범위 지정  constructorMax ~ n
		
		int ans = Integer.MAX_VALUE;
		
		for (int i = constructorMax; i <= n; i++) {
			String tempInt = String.valueOf(i);
			int tempConstr = i;
			
			for (int j = 0; j < tempInt.length(); j++) {
				tempConstr += tempInt.charAt(j)-'0';
			}
			
			if(tempConstr == n) {
				ans = Math.min(ans, i);
			}else {
				ans = 0;
			}
			
		}
		
		System.out.println(ans);
		*/

	}

}
