package january;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2064 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int ip[] = new int[1000];

        for (int i = 0; i < num; i++) {
            String str = br.readLine();
            StringTokenizer stoken = new StringTokenizer(str, ".");
            for (int j = 0; j < 4; j++) {
                int a=Integer.parseInt(stoken.nextToken());
                ip[i] <<= 8;
                ip[i] |= a;
            }
        }
        int subnet = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = 1 << i;
            boolean end = false;
            for (int j = 1; j < num; j++) {
                if ((ip[0] & bit) != (ip[j] & bit)) {
                    end = true;
                    break;
                }
            }
            if (end) break;
            else subnet |= bit;
        }
        print(ip[0] & subnet);
        print(subnet);
    }
    
    static void print(int mask) {
        int shift = 24;
        for (int i = 0; i < 4; i++, shift -= 8) {
            int num =((mask >> shift) & (1 << 8) - 1);
            System.out.print(num);
            if (i != 3) System.out.print(".");
        }
        System.out.println();
    }
}
