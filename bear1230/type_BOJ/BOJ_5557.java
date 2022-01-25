import java.io.*;
import java.util.*;

public class Main{
	public static int n;
	public static long[][] arr = new long[2][21];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
        
		arr[0][m] = 1;
		for (int i = 0; i < n - 2; i++){
			m = Integer.parseInt(st.nextToken());
			for (int j = 0; j <= 20; j++){
				if (arr[i % 2][j] > 0){
					if (j + m >= 0 && j + m <= 20){
						arr[(i + 1) % 2][j + m] += arr[i % 2][j];
					}
					if (j - m >= 0 && j - m <= 20){
						arr[(i + 1) % 2][j - m] += arr[i % 2][j];
					}
				}
			}
			Arrays.fill(arr[i % 2], 0);
		}
		bw.write(arr[n % 2][Integer.parseInt(st.nextToken())] + "\n");
		bw.flush();
		bw.close();
	}
}