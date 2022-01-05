import java.io.*;
import java.util.*;

public class Main{
    static int t;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t -- > 0){
            int n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i =1; i<=n; i++){
                arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
            }

            int ans = -(1<<19);
            for(int k = 1; k <= n; k++){
                for(int i =0; i <= n-k; i++){
                    ans = Math.max(ans, arr[i+k] - arr[i]);
                }
            }
            sb.append(ans + "\n");
        }
        
        System.out.println(sb);
    }
}