package BOJ;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().replaceAll("[0-9]", ""); 
        String pattern = br.readLine();

        int ans = kmp(str, pattern);

        System.out.println(ans);
    }

    public static int kmp(String str, String pattern) {
        int[] pi = getPi(pattern);
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int j = 0;

        for(int i=0; i<s.length; i++) {

            while(j>0 && s[i]!=p[j]) {
                j = pi[j-1];
            }

            if(s[i]==p[j]) {
                if(j==p.length-1)
                    return 1;
                else
                    j++;
            }
        }

        return 0;
    }

    public static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        char[] p = pattern.toCharArray();
        int j = 0;

        for(int i=1; i<pattern.length(); i++) {
            while(j>0 && p[i]!=p[j]) {
                j = pi[j-1];
            }

            if(p[i]==p[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}