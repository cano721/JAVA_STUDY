package dynamicProgramming;

import java.io.*;
import java.util.*;

/*
 * A : 1 2 3 4 6 7 9 10
 * B : 8 2 9 1 4 6 7 10
 * 
 * 오른쪽도 오름차순으로 계속 증가한다면 전깃줄은 엉키지 않음 = 설치 가능한 최대 개수
 * 즉, 최장 증가 수열(LIS)
 * 
 * {1,4,6,7,10} => 5 임으로, 8-5 = 3이 철거해야하는 최소 전선 수이다.
 * 
 * 따라서 철거해야하는 최소 전기줄 수 = (전체 전선 수 - 설치가능한 최대 수)
 * 
 * dp[n] : n번째 수를 마지막 문자로 가지는 LIS의 길이
 * 
 * 
 * 1. dp 배열 1로 초기화
 * 2. i번째와 그 보다 왼쪽에 있는 수의 크기를 비교
 * 3. 오른쪽 값이(i 번째 수) 크다면 증가하는 순서임으로,dp배열 업데이트/ 만약 현재  i번째 dp의 값 보다 j번째 dp의 값에 1을 더한게 클 경우에만 업데이트 해줌
 * 4. 계속 돌면서 가장 큰 길이의 LIS 구하기
 * 5. 전체 전선 수 - 가장 큰 길이의 LIS = 최소 전깃줄 수
 * 
 */

public class BJ2565_전깃줄 {

	static int cnt;
	static int wire[][];
	static Integer dp[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		cnt = Integer.parseInt(br.readLine());
		
		//wire[cnt][0] : A전깃줄
		//wire[cnt][1] : B전깃줄
		wire = new int[cnt][2]; 
		dp = new Integer[cnt];
		
		for (int i = 0; i < cnt; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			wire[i][0] = Integer.parseInt(st.nextToken());
			wire[i][1] = Integer.parseInt(st.nextToken());
			
		}
		
		//A전깃줄 기준으로 정렬
		Arrays.sort(wire, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int max=Integer.MIN_VALUE;
		for (int i = 0; i < cnt; i++) {
		
			//최소  개수 1로 초기화
			dp[i] = 1;
			
			for (int j = 0; j < i; j++) {
				if(wire[i][1] > wire[j][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		for (int i = 0; i < cnt; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(cnt-max);
	}

}
