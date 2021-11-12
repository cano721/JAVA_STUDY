import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int n = Integer.parseInt(br.readLine());
		
		int[] num = new int[n * n];
		
		int tmp = 0;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				num[tmp++] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(num);
		
		System.out.print(num[num.length - n]);
	}
}