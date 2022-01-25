import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            
            st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> q = new PriorityQueue<>();
            
            for (int j = 0; j < m; j++) {
                q.add(Long.parseLong(st.nextToken()));
            }
            long result = 0;
            while (q.size() >= 2) {
                long idx = q.poll() + q.poll();
                
                result += idx;
                q.offer(idx);
            }

            System.out.println(result);
        }
    }
}
