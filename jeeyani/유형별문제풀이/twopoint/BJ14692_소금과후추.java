package 유형별문제풀이.twopoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ14692_소금과후추 {

	static int n,m,k,w;
	static int pixel[][], pixelB[][];
	static List<Integer> median = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		pixel = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				pixel[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pixelB = new int[n-w+1][m-w+1];
		
		for (int i = 0; i < n-w+1; i++) {
			for (int j = 0; j < m-w+1; j++) {
				//리스트 초기화
				median.clear();
				
				//B행렬 구하기
				medianCheck(i,j);
				pixelB[i][j] = median.get(median.size()/2);
				System.out.print(pixelB[i][j]+" ");
				
			}
			System.out.println();
		}

	}

	private static void medianCheck(int i, int j) {
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < w; y++) {
				median.add(pixel[i+x][j+y]);
			}
		}
		Collections.sort(median);
	}

}
