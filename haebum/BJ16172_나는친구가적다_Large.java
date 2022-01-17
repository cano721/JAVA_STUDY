/**
 * 
 * KMP 사용 풀이
 * 
 * 1. 정규식으로 숫자 제거(replaceAll 시간복잡도 O(N))
 * 
 * 2. KMP 실행 O(N+M)
 * 
 * 총 시간복잡도 O(N+M)
 */
import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class BJ16172_나는친구가적다_Large {
    
    public static int[] pi;

    public static String str, pattern;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        str = br.readLine();
        
        str = str.replaceAll("[0-9]", "");
        
        pattern = br.readLine();

        pi = new int[pattern.length()];

        getPi();
        kmp();

        bw.flush();
        bw.close();
    }

    public static void getPi(){
        int j = 0;

        for(int i = 1; i < pattern.length(); i++){
            if(j > 0 && pattern.charAt(i) != pattern.charAt(j)){
                j = pi[j-1];
            }

            if(pattern.charAt(i) == pattern.charAt(j)){
                pi[i] = ++j;
            }
        }
    }

    public static void kmp(){
        int j = 0;

        for(int i = 0; i < str.length(); i++){

            if(j > 0 && str.charAt(i) != pattern.charAt(j)){
                j = pi[j-1];
            }

            if(str.charAt(i) == pattern.charAt(j)){
                if(j == pattern.length()-1){
                    System.out.println(1);
                    return;
                }else{
                    j++;
                }
            }
        }
        System.out.println(0);
    }
}

