import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BitSet bit = new BitSet();
        while (st.hasMoreTokens()) {
            int m = Integer.parseInt(st.nextToken());
            if (bit.get(m)) continue;
            bit.set(m);
            bw.write(m + " ");
        }
        bw.flush();
    }
}