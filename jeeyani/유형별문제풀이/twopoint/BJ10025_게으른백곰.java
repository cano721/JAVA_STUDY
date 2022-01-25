package 유형별문제풀이.twopoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10025_게으른백곰 {

	static int n,k;
	static int[] basket = new int[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			basket[x] = g;
		}
		
		//초기값 range
		int range = 0;
		for (int i = 0; i < basket.length && i<k+1; i++) {
			range +=basket[i];
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < basket.length; i++) {
			//현재 곰의 위치를 기준으로 좌우 범위 지정
			int start = i-k-1;
			int end = i+k+1;
			
			if(start >= 0) {
				range -=basket[start];
			}
			if(end < basket.length) {
				range += basket[end];
			}
			
			max = Math.max(max, range);
		}
		System.out.println(max);
	}

}
