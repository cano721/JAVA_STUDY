import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {

       		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> q = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine(), " ");
        
		for(int i = 0; i < n; i++) {
			q.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i = 0; i < m; i++) {
			long num1 = q.poll();
			long num2 = q.poll();
			
			q.add(num1 + num2);
			q.add(num1 + num2);
		}
		
		long sum = 0;
		while(!q.isEmpty()) {
			sum += q.poll();
		}
		System.out.println(sum);
    }
}