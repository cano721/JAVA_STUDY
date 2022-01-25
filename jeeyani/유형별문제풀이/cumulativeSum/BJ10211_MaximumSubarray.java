package 유형별문제풀이.cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 * 배열을 순차적으로 모두 조회하면서 그 중 누적합이 최대가 되는 구간찾기
 * 
 * 
 @author Jeeyani
 */

public class BJ10211_MaximumSubarray {

	static int n, k;
	static int[] arr;
	static int[] psum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {

			n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			arr = new int[n];
			psum = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int maxResult = getPrefixSum();
			sb.append(maxResult+"\n");
			
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static int getPrefixSum() {
		
		int max = arr[0];
		psum[0]=arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			
			//합이 음수인 경우 값을 0으로 고정
			//누적합이 음수가 되는 순간 그 뒤에 있는 수들은 새로운 누적합(0)을 구하는 것이 최대값을 구할 수 있기 때문
			if(psum[i-1] < 0) {
				psum[i-1] = 0;
			}
			//누적 합
			psum[i] = psum[i-1] + arr[i];
			max = Math.max(max, psum[i]);
			
		}
		return max;
	}

}
