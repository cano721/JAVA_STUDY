package cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 
 * arr[i]~arr[j]까지의 합 => pSum[j] - pSum[i-1]
 * 
 *  pSum[j] - pSum[i-1] = k라고 할때
 *  
 * pSum[j]%m - pSum[i-1]%m = k%m
 * pSum[j]%m - pSum[i-1]%m = 0   임으로
 * 
 * 따라서 
 * pSum[j] = pSum[i-1] 인 경우의 수 찾기
 * 
 * 
 @author Jeeyani
 */

public class BJ10986_나머지합 {

	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n + 1];

		int[] count = new int[m];

		for (int i = 1; i <= n; i++) {
			arr[i] += Integer.parseInt(st.nextToken()) % m;
			
			count[arr[i]%m]++;
		}

		/*
		 * 각 나머지 수에서 2개를 뽀는 경우의 수를 모두 더하기
		 * 
		 * ** 조합식 사용하기
		 *  nCr = nPr/r! = n!/(r!(n-r)!)
		 * 
		 */
		long result = count[0];
		
		for (int i = 0; i < count.length; i++) {
			result += (long)count[i] * (count[i]-1)/2;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}
}
