import java.io.*;
import java.util.*;
 /**
  *  부모 노드 -> (자식 번호 - 2)/k +1
  *  k == 1인 경우 (자식 번호 - 2)/k +1 할 경우 최대 n번 돌게 되어 시간초과.
  */
public class Main {

    static int  k, q;
    static long n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<q; i++){
            st= new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            if(k == 1) sb.append(Math.abs(y-x)+"\n");
            else sb.append(pro(y,x) + "\n");
        }

        System.out.println(sb);
    }

    static int pro(long y, long x){
        int result = 0;
        while(y != x){
            if(y > x) y = (y-2)/k +1;
            else x = (x-2)/k+1;
            result++;
        }
        return result;
    }
    
}