package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());
                
        String str = Integer.toBinaryString(X);
        int answer = 0;
        for(int i = 0; i < str.length(); i++) {
        	if(str.charAt(i) == '1') answer++;
        }
        
        System.out.println(answer);
	}
}