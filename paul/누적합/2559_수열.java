import java.io.*;
import java.util.*;

public class Main{
    static int n, k;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1; i <n+1; i++){
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }

        long ans = -(1<<30);
        for(int i = 0; i<= n-k; i++){
            ans = Math.max(ans, arr[i+k]-arr[i]);
        }
        System.out.println(ans);
    }
}