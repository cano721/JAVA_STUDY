package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        
        
        // 종유석과 석순 나누기
        int[] upside = new int[H];
        int[] downside = new int[H];
        
        for(int i = 0; i < N/2; i++) {
        	downside[Integer.parseInt(br.readLine())]++;
        	upside[Integer.parseInt(br.readLine())]++;
        }
        
        int[] DP_upside = new int[H];
        int[] DP_downside = new int[H];
        
        for(int i = 1; i < H; i++) {
        	DP_upside[i] = DP_upside[i-1]+upside[i];
        	DP_downside[i] = DP_downside[i-1]+downside[i];
        }
        
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        
        for(int i = 1; i <= H; i++) {
        	int temp = DP_downside[H-1]-DP_downside[i-1];
        	temp += DP_upside[H-1]-DP_upside[H-i];
        	
        	if(temp < min) {
        		min = temp;
        		cnt = 1;
        	}else if (temp == min) {
        		cnt++;
        	}
        	
        }
        
        System.out.println(min+" "+cnt);
	}
}