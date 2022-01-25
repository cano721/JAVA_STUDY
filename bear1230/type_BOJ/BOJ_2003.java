import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;

		int start = 0;
		int end = 0;

		int sum =0;

        
        while(true){		
			if(sum >= m) {
				sum -=arr[start];
				start++;
			}
			else if(end >= n) break; 
			else{	
				sum += arr[end];
				end++;
			}
			if(sum == m) {
				cnt++;
			}
        }
    
		System.out.println(cnt);
	}

}