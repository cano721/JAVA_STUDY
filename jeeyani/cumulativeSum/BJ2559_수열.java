package cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * arr[i]~arr[j]까지의 합 => pSum[j] - pSum[i-1]
 * 
 * pSum[n] = pSum[n-1] + arr[n]
 * 
 * 
 @author Jeeyani
 */

public class BJ2559_수열 {

	static int n, k;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//첫 k구간 까지의 누적 합 구하기
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += arr[i];
		}

		int max = sum;
		
		//n개의 누적합을 계속해서 갱신
		for (int i = k; i < n; i++) {
			sum = sum - arr[i-k] + arr[i];
			
			max = Math.max(max, sum);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
