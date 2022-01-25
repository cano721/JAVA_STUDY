import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[41][2];
		
		arr[0][0] = 1;
		arr[0][1] = 0;
		arr[1][0] = 0;
		arr[1][1] = 1;
		

		for(int i = 2; i < 41; i++) {
			for(int j = 0; j < 2; j++) {

                arr[i][j] = arr[i - 1][j] + arr[i - 2][j];
			}
		}
		
		for(int i = 0; i < n; i++){
            int idx = Integer.parseInt(br.readLine());
			bw.write(arr[idx][0] + " " + arr[idx][1] + "\n");
        }
        bw.flush();
        br. close();
        bw.close();
        

	}
}