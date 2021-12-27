package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/* 참조
 * https://manzoo.tistory.com/88
 * */
public class n11812_k진트리 {
	 public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        long N = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
         
        for (int i = 0; i < Q; i++) {
        	st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
             
            long count = 0;
            if (K == 1) {
                count = x < y ? y - x : x - y;
            }
            else {
                while (x != y) {
                    // 부모 노드
                    if (x < y) {
                        y = (y - 2) / K + 1;
                    }
                    else {
                        x = (x - 2) / K + 1;
                    }
                     
                    count++;
                }
            }           
             
            System.out.println(count);
        }
    }
}
