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
 * arr[i]~arr[j]까지의 합 => pSum[j] - pSum[i-1]
 * 
 * pSum[n] = pSum[n-1] + arr[n]
 * 
 * pSum[j] - pSum[i-1] = k가 되는 경우의 수를 찾기 위해서
 * pSum[ㅓ] - k = pSum[i-1]를 이용
 * 
 * 
 * 참고)https://velog.io/@redcarrot01/ProblemSolving-%EB%B0%B1%EC%A4%80-2015-%EC%88%98%EB%93%A4%EC%9D%98%ED%95%A94-%EA%B5%AC%EA%B0%84%ED%95%A9
 * 
 @author Jeeyani
 */

public class BJ2015_수들의합4 {

	static int n, k;
	static int[] arr;
	static int[] pSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[n+1];
		pSum = new int[n+1];

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			pSum[i] = pSum[i-1]+arr[i];
		}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0,1);
		
		long result = 0;
		for (int i = 1; i <= n; i++) {
			result += map.getOrDefault(pSum[i]-k, 0);
			
			map.put(pSum[i], map.getOrDefault(pSum[i], 0)+1);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

}
