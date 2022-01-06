import java.io.*;
import java.util.*;
public class Main {   
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        
        while(t --> 0) {
            int n = Integer.parseInt(br.readLine());
            int[] sum = new int[n+1];       
            st = new StringTokenizer(br.readLine());
            
            for(int i=0; i<n; i++) {
                int num = Integer.parseInt(st.nextToken());
                sum[i+1] = sum[i] + num;
            }
            
            int max = Integer.MIN_VALUE;
            for(int i = 1; i <= n; i++){
               for(int j = i; j <= n; j++){
                    max = Math.max(max, sum[j] - sum[i-1]);
                } 
            }       
            System.out.println(max);
        }
    }
}