import java.util.*;
import java.io.*;

public class Main {
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int k =0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i =0; i<n; i++){
            k = Integer.parseInt(br.readLine());
            pq.add(k);
        }
        
        pro(h,t,pq);

    }

    static void pro(int h, int t, PriorityQueue<Integer> pq){
       int ans = 0;
        while(t-- > 0){
           if(pq.peek() < h || pq.peek() == 1) break;
           ans++;
           int top = pq.poll();
           top /= 2;
           pq.add(top);
       }

       if(pq.peek() < h){
           sb.append("YES").append('\n');
           sb.append(ans).append('\n');
       }else{
           sb.append("NO").append('\n');
           sb.append(pq.peek()).append('\n');
       }

       System.out.println(sb);
    }
}