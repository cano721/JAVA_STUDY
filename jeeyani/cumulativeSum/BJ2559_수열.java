package cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ2559_수열 {

	static int n, k;
	static int[] arr;
	static int[] sum;

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

		int start = 0;
		int end = 0;
		int sum = 0;
		int lenCnt = 0;
		
		int maxResult = 0;
		for (int i = start; i < n; i++) {

			while (lenCnt < k && end < n) {
				sum += arr[end];
				end++;
				lenCnt++;
			}

			maxResult = Math.max(maxResult, sum);

			sum -= arr[start];
			start++;
			lenCnt--;

		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(maxResult);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
