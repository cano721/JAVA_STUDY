package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n23635_산타로부터의선물 {
	static int k, n;
	static int[] enjoy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        enjoy = new int[n];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < n; i++) {
        	 enjoy[i] = Integer.parseInt(st.nextToken());
		}
        
        
	}

}
