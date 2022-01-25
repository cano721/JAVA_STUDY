import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
        StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i <n; i++) {
            st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		floyd();
		for (int i = 0; i < n; i++) {           
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	private static void floyd() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(map[j][i] == 0 || map[i][k] == 0) continue;
					if(map[j][k] == 0) {
						map[j][k] = 1;
					}
				}
			} 
		}
		
	}
}
