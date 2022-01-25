import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine().replaceAll("[0-9]", "");;
        String K = br.readLine();

        char[] s = S.toCharArray();
        char[] p = K.toCharArray();
        
        int[] pi = new int[S.length()];

        // pi 구하기
        int j = 0;
        for (int i=1; i< p.length; i++) {
            while (j > 0 && p[i] != p[j]) {
                j = pi[j-1];
            }

            if (p[i] == p[j]) {
                pi[i] = ++j;
            }
        }

        j = 0;
        // KMP 알고리즘 수행
        for (int i=0; i<s.length; i++) {
            while (j > 0 && s[i] != p[j]) {
                j = pi[j-1];
            }

            if (s[i] == p[j]) {
                if (j == p.length - 1) {
                    System.out.println(1);
                    return;
                    // j = pi[j];
                }
                else {
                    j++;
                }
            }
        }

        System.out.println(0);
    }
}