import java.util.*;
import java.io.*;

public class Main {
    static int n, k, x_min = Integer.MAX_VALUE, x_max = 0;
    static int[] arr = new int[1000010];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());

       for(int i =0; i< n; i++){
           st= new StringTokenizer(br.readLine());
           int g = Integer.parseInt(st.nextToken());
           int x = Integer.parseInt(st.nextToken());
           arr[x] = g;
       }
     
    }

    public static void pro(){
        int ans = 0, cnt = 0;
        k = 2*k -1; 
        for(int i =0; i<= 1000000; i++){
            cnt += arr[i];
            if( i > 2*k) cnt -= arr[i-2*k];
            ans = Math.max(cnt, ans);
        }
        System.out.println(ans);
    }

}