package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * int 형은 –2,147,483,648 ~ 2,147,483,647
*/
public class n15903_카드합체놀이 {
	static int n, m;
	static long answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine()," ");
        
        for (int i = 0; i < n; i++) {
        	pq.add(Long.parseLong(st.nextToken()));
		}
        
        long sum = 0;
        for (int i = 0; i < m; i++) {
			sum = pq.poll() + pq.poll();
			pq.add(sum);
			pq.add(sum);
		}
        while(!pq.isEmpty()){
			answer += pq.poll();
		}
        
        System.out.println(answer);
	}

}
