import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		int arr[] = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) 
            arr[i] = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = 0;
		int minLen = Integer.MAX_VALUE;
		int sum = 0;
		
		while(true) {
			if(sum >=s) {
				minLen = Math.min(minLen, end-start);
				sum -= arr[start++];
			}
			else if(end == n) break;
            
			else if(sum < s) {
				sum += arr[end++];
			}
		}
		
		if(minLen == Integer.MAX_VALUE){
            System.out.println(0);
        }else{
            System.out.println(minLen);
        }
	}

}