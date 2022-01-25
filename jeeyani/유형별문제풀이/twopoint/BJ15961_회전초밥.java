package 유형별문제풀이.twopoint;

import java.io.*;
import java.util.*;

/*
 * K개라는 범위값이 계속 지정되어 움직임
 * 즉, 고정적인 부분 배열의 크기를 나타내는 변수(K)가 존재함으로 포인터 하나만으로 가능
 * 
 @author Jeeyani
 */

public class BJ15961_회전초밥 {

	static int n, d, k, c;
	static int[] dish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); //접시갯수
		d = Integer.parseInt(st.nextToken()); //초밥가짓수!!
		k = Integer.parseInt(st.nextToken()); //연속접시 수!!
		c = Integer.parseInt(st.nextToken());

		dish = new int[n];

		for (int i = 0; i < n; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}

		int[] checkSuhsi = new int[d + 1];

		int maxCnt = 0;
		int max = 0;

		//k개씩 고르기(초기화)
		for (int i = 0; i < k; i++) {
			//같은 가짓수를 제외한 초밥종류 카운팅
			if (checkSuhsi[dish[i]] == 0) maxCnt++;
			
			checkSuhsi[dish[i]]++;
		}

		max = maxCnt;
		for (int i = 1; i < n; i++) {
			/*
			 * 가장 많이 담을 수 있는 경우는 쿠폰 종류를 포함한 경우가 제일 많은 경우임
			 * (k=4일때 될 수 있는 가장 많은 경우의 max는 5가 가장 큰 경우)
			 */
			if (max <= maxCnt) {
				//쿠폰 메뉴가 없을 경우
				if (checkSuhsi[c] == 0) {
					max = maxCnt + 1;
				} else {
					//쿠폰 메뉴가 있을 경우 전체 가짓수 유지
					max = maxCnt;
				}
			}
			
			/*
			 * k만큼 범위이동해주기
			 */
			
			//맨 첫번째 담은 초밥 빼기
			checkSuhsi[dish[i - 1]]--;

			//가짓수 수도 빼기
			if (checkSuhsi[dish[i - 1]] == 0) maxCnt--;

			//그 다음 초밥 담기
			if (checkSuhsi[dish[(i + k - 1) % n]] == 0) maxCnt++;
			checkSuhsi[dish[(i + k - 1) % n]]++;
		}
		System.out.println(max);
	}

}
