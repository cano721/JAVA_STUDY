import java.io.*;
import java.util.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(getAns(s));
    }

    static int getAns(String s){
        int max = 0;
        int n = s.length();
        for(int k =0; k<n; k++){
            String p = s.substring(k, n);
            int m = p.length();
            int[] fail = new int[m];

            for(int i =1, j=0; i<m; i++){
                while(j>0 && p.charAt(i) != p.charAt(j)) j = fail[j-1];
                if(p.charAt(i) == p.charAt(j)) {
                    fail[i] = ++j;
                    max = Math.max(max, fail[i]);
                }
            }
        }
        return max;
    }
}