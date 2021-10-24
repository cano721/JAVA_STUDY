import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] dp = new int [21];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<21;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        System.out.println(dp[n]);
    }
}
