package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
 * 
 * dp[n] = k : k번째 연주에서 볼륨 n으로 연주할 수 있음
 * 
 * 
 * [예제]
 * 3 5 10
 * 5 3 7
 * 
 * 0번째 연주에서 가능한 볼륨 dp[5] = 0
 * 1번째 연주에서 가능한 볼륨 dp[0] = 1 ,dp[10] = 1
 * 2번째 연주에서 가능한 볼륨 dp[7] = 2 ,dp[3] = 2
 * 3번째 연주에서 가능한 볼륨 dp[10] = 3, dp[0] = 3
 * 
 * 따라서 최대값은
 * 원소가 N이 될때의 가장큰 인덱스의 값
 * 
 */

public class BJ1495_기타리스트 {

	static int n,s,m,volume[];
	static int dp[];
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		volume = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			volume[i] = Integer.parseInt(st.nextToken());
		}
		
		//dp초기화
		dp = new int[m+1];
		for (int i = 0; i <=m; i++) {
			dp[i] = -1;
		}
		
		dp[s] = 0;
		
		for (int i = 1; i <= n; i++) {
			int v = volume[i];
			List<Integer> volList = new ArrayList<>();
			
			for (int j = 0; j <=m; j++) {
				//i-1번째 연주에서의 가능한 볼륨
				if(dp[j] == i-1) {
					int tempVol1 = j+v;
					int tempVol2 = j-v;
					
					if(0 <= tempVol1 && tempVol1 <=m) volList.add(tempVol1);
					if(0 <= tempVol2 && tempVol2 <=m) volList.add(tempVol2);
					
				} 
				
			}
			//i번째 연주에서 가능한 볼륨의 인덱스에 값 갱신해주기
			for(int n : volList) {
				dp[n] = i;
			}
			
		}
		//중간에 볼륨 조절을 할 수 없는 경우 검토하기
		int ans = -1;
		for (int i = 0; i <=m; i++) {
			if(dp[i] == n) {
				ans = Math.max(ans, i);
			}
		}

		System.out.println(ans);
	}

}
