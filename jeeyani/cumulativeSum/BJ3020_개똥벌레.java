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

	static int n, h, min,cnt;
	//static int[] cave;
	//static int[] rangeSum;
	static int[] rangeDown;
	static int[] rangeTop;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		min = n;

		//cave = new int[n+1];
		//rangeSum = new int[h+1];

		rangeDown = new int[h+1]; //종유석
		rangeTop = new int[h+1]; //석순

		for (int i = 1; i < n+1; i++) {
			//cave[i] = Integer.parseInt(br.readLine());
			int height = Integer.parseInt(br.readLine());
			
			if(i%2==0) {
				rangeDown[height]++;
			}
			else {
				rangeTop[height]++;
			}
		}
		
		
		getRangeMin();
		
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(min+" "+cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static void getRangeMin() {
		
		int[] downSum = new int[h+1];
		int[] topSum = new int[h+1];
		
		//누적합 계산하기
		for (int i = 1; i <=h; i++) {
			
			downSum[i] = downSum[i-1]+rangeDown[i];
			topSum[i] = topSum[i-1]+rangeTop[i];
			
		}
		
		for (int i = 1; i <=h; i++) {
			//부수고 가는 벽의 수
			int wallCnt = 0;
			
			// pSum[n] = pSum[n-1] + arr[n] 공식 이용
			wallCnt +=downSum[h]-downSum[i-1];
			wallCnt +=topSum[h]-topSum[i-1];
			
			if(min>wallCnt) {
				min = Math.min(wallCnt, min);
				cnt = 1;
			}
			else if(min == wallCnt) cnt++;
		}
		
	}

}
