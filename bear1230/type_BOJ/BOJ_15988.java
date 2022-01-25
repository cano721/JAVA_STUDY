import java.io.*;
import java.util.*;

public class Main {
    static long mod = 1000000009L;
    static int limit = 1000000;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        long[] d = new long[limit+1];
        d[0] = 1;
        for (int i=1; i<=limit; i++) {
            for (int j=1; j<=3; j++) {
                if (i-j >= 0) {
                    d[i] += d[i-j];
                }
            }
            d[i] %= mod;
        }
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            bw.write(String.valueOf(d[n]));
            bw.write('\n');
            bw.flush();
        }
    }
}
