import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
		
        
		while(n-->0) 
            q.add(Integer.parseInt(br.readLine()));
        
		while (cnt < t && q.peek() >= h && q.peek() > 1){
			cnt++;
			q.add((Math.max(1, q.poll()/2)));
		}
		if(h > q.peek()) {
			System.out.println("YES\n"+cnt);
		}else {
			System.out.println("NO\n"+q.peek());
		}
	}
}