import java.util.*;
import java.io.*;

public class Main {
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t =  Integer.parseInt(br.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for(int i =0; i<n; i++){
                Long k = Long.parseLong(st.nextToken());
                pq.add(k);
            }
            pro(pq);
        }

        System.out.println(sb);

    }

    static void pro(PriorityQueue<Long> pq){
        long ans =0L;
        while(pq.size() > 1){
            long num1= pq.poll();
            long num2 = pq.poll();
            ans += num1+num2;
            pq.add(num1+num2);
        }
        
        sb.append(ans).append('\n');
    }
}