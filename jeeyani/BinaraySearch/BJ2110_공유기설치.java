package BinaraySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 
 * **모든 공유기들의 사이 간격이 공평하게 설치되는 것!!
 * 
 * 파라메트릭서치(이분탐색 응용)
 * 1. 특정 간격을 기준으로 가능한 위치에 공유기를 설치
 * 2. 설치한 후, 3번 4번 과정처럼 판단
 * 3. 공유기 수가 더 설치되어야 하면 간격줄이기
 * 4. 공유기 수를 줄여야하면 간격 늘리기
 * 
 * 
 */

public class BJ2110_공유기설치 {

	static int n,c;
	static int[] home;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		home = new int[n];
		
		for (int i = 0; i < n; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(home);

		int ans = getClosedRouter();
		
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static int getClosedRouter() {
		int left = 1; //가능한 최소 간격
		int right = home[n-1] - home[0]; //가능한 최대 간격
		int mid = 0;
		int cnt = 0;
		int ans = 0;
		int dist = 0;
		
		while(left <= right) {
			
			mid = (left+right)/2;
			int start = home[0];
			cnt = 1;
			
			//dist 를 기준으로 비교
			for (int i = 1; i < n; i++) {
				dist = home[i] - start;
				
				//dist값이 임의의 mid값보다 크거나 같으면 공유기 설치
				if(mid <= dist) {
					cnt++;
					start = home[i];
				}
			}
			
			//공유기 수보다 크거나 같으면, 공유기 수 저장 및 간격 넓히기
			if(cnt >= c) {
				ans = mid;
				left = mid+1;
			}
			//간격 줄이기
			else {
				right = mid - 1;
			}	
		}
		
		
		return ans;
	}

}
