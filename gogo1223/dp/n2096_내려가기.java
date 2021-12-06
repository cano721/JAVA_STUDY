package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2096_내려가기 {
	static int n;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][3];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        
        for (int i = 0; i < n; i++) {
			if(i == 0) {
				//초기값 설정
				maxDp[0] = minDp[0] = arr[i][0];
				maxDp[1] = minDp[1] = arr[i][1];
				maxDp[2] = minDp[2] = arr[i][2];
			} else {
				//최대값 구하기
				int bfMax0 = maxDp[0], bfMax2 = maxDp[2];
				maxDp[0] = Math.max(maxDp[0], maxDp[1]) + arr[i][0];
				maxDp[2] = Math.max(maxDp[2], maxDp[1]) + arr[i][2];
				maxDp[1] = Math.max(Math.max(bfMax0, maxDp[1]), bfMax2) + arr[i][1];
				//최소값 구하기
				int bfMin0 = minDp[0], bfMin2 = minDp[2];
				minDp[0] = Math.min(minDp[0], minDp[1]) + arr[i][0];
				minDp[2] = Math.min(minDp[2], minDp[1]) + arr[i][2];
				minDp[1] = Math.min(Math.min(bfMin0, minDp[1]), bfMin2) + arr[i][1];
			}
		}
        //출력
        int answerMax = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int answerMin = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));
        System.out.println(answerMax+" "+answerMin);

	}

}
