package twoPoinSlider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n14602_소금과후추 {
	static int m, n, k, w;
	static int[][] arr, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		arr = new int[m][n];
		answer = new int[m-w+1][n-w+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//구현부
		for (int i = 0; i < m-w+1; i++) {
			for (int j = 0; j < n-w+1; j++) {
				answer[i][j] = median(i, j);
				bw.write(answer[i][j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();

	}
	//중간값 구하기
	private static int median(int x, int y) {
		int[] sortArr = new int[w*w];
		int p = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < w; j++) {
				sortArr[p++] = arr[x+i][y+j];
			}
		}
		Arrays.sort(sortArr);
		
		return sortArr[w*w/2];
	}

}
