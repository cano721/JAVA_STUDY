package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        BitSet bs = new BitSet(33554433);
        
        while(st.hasMoreTokens()) {
        	int temp = Integer.parseInt(st.nextToken());
        	if(!bs.get(temp)) bw.write(temp+" ");
        	bs.set(temp);
        }
        bw.flush();
        
	}
}