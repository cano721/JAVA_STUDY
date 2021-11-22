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
           x_min = Math.min(x_min, x);
           x_max = Math.max(x_max, x);
       }
     
    }

    public static void pro(){
        long ans = 0, cnt = 0;
        if (2*k >= x_max){
            for(int i =x_min; i<=x_max; i++){
                ans +=  arr[i];
            }
        }else{
            for(int i =0; i<= 2*k; i++){
                ans += arr[i];
            }
            cnt = ans;
            int left= 0, right = 2*k;
            while(right <= x_max+1){
                cnt -= arr[left++];
                cnt += arr[++right];
                ans = Math.max(ans, cnt);
            }
        }
        System.out.println(ans);
    }

}