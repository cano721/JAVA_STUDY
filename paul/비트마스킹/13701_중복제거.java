import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BitSet bit = new BitSet(1<<25);
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            if(!bit.get(n)){
                bit.set(n);
                sb.append(n + " ");
            }
        }

        System.out.println(sb);
    }
}