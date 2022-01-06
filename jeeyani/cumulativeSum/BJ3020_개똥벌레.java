package cumulativeSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 * 
 @author Jeeyani
 */

public class BJ3020_개똥벌레 {

	static int n, h, min=Integer.MAX_VALUE;
	static int[] cave;
	static int[] rangeSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		cave = new int[n+1];
		rangeSum = new int[h+1];

		for (int i = 1; i < n+1; i++) {
			cave[i] = Integer.parseInt(br.readLine());
		}
		
		
		getRangeMin();
		
		int cnt=0;
		for (int i = 1; i <= h; i++) {
			if(min == rangeSum[i]) {
				cnt++;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(min+" "+cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static void getRangeMin() {
		
		for (int i = 1; i <= h; i++) {
			int wallCnt=0;
			for (int j = 1; j <=n; j++) {
				
				if(j%2 == 0) {
					if(Math.abs(cave[j]-h) < i) wallCnt++;
					
				}else {
					if(cave[j] >= i) wallCnt++;
				}
			}
			rangeSum[i] = wallCnt;
			min = Math.min(min, rangeSum[i]);
		}
		
	}

}
