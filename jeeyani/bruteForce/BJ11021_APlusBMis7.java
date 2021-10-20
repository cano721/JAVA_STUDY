package bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class BJ11021_APlusBMis7 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.valueOf(br.readLine());
		
		StringTokenizer st ;
		
		for (int i = 1; i <= tc; i++) {
		
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			

			System.out.println("Case #"+i+": "+(a+b));
			
		}
	}

}
