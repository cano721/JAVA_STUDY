import java.io.*;
import java.util.*;

public class Main{
    static int n, h;
    static int[] bottom;
    static int[] ceil;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        bottom = new int[h+1];
        ceil = new int[h+1];

        for(int i =0; i<n/2; i++){
            bottom[Integer.parseInt(br.readLine())]++;
            ceil[Integer.parseInt(br.readLine())]++;
        }

        for(int i=h-1; i>0; i--){
            bottom[i] += bottom[i+1];
            ceil[i] += ceil[i+1];
        }

        int ans = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i =1; i<= h; i++){
            int k = ceil[i]+bottom[h-i+1];
            if( ans > k){
                ans = k;
                cnt = 1;
            }
            else if(ans == k){
                cnt++;
            }
        }

        System.out.println(ans +" " + cnt);
    }
}