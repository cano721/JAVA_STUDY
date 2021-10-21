package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ7568_덩치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][2];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			int rank = 1;
			for (int j = 0; j < n; j++) {

				if (i == j) continue;
				
				else if (arr[i][0] < arr[j][0] && arr[i][1] < arr[j][1]) {
					rank++;
				}
			}

			System.out.print(rank + " ");
		}

	}
}
