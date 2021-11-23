package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n9184_신나는함수실행 {
	static int[][][] arr = new int[21][21][21];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(n == -1 && m == -1 && k == -1) break;
			
			bw.write("w(" + n + ", " + m + ", " + k + ") = ");
			int answer = w(n, m, k);
			bw.write(answer+"\n");
		}
		br.close();
		bw.flush();
		bw.close();

	}

	private static int w(int a, int b, int c) {
		if(a>=0 && b>=0 && c>=0 && a<=20 && b<=20 && c<=20 && arr[a][b][c] != 0) {
			return arr[a][b][c];
		}
		if(a <= 0 || b <= 0 || c <= 0 ) {
			return 1;
		}
		if(a > 20 || b > 20 || c > 20 ) {
			return arr[20][20][20] = w(20,20,20);
		}
		if(a < b && b < c) return arr[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
		return arr[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
	}

}
