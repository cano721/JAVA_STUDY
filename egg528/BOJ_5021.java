package BOJ;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;


public class Main{	
	static boolean[][] ladder;
	static boolean[][] visited;
	static int ans = -1;
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
     
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        
        String king = br.readLine();
        map.put(king, BigDecimal.valueOf(1));
        
        ArrayList<String> order = new ArrayList<String>();
        for(int i = 0; i < N; i++) {
        	order.add(br.readLine());
        }
        
        for(int i = 0; i < order.size(); i++) {
        	st = new StringTokenizer(order.get(i));
        	
        	String child = st.nextToken();
        	String parent1 = st.nextToken();
        	String parent2 = st.nextToken();
        	
        	BigDecimal p1_bd = map.getOrDefault(parent1, BigDecimal.valueOf(0));
        	BigDecimal p2_bd = map.getOrDefault(parent2, BigDecimal.valueOf(0));
        	
        	map.put(child, p1_bd.multiply(BigDecimal.valueOf(0.5)).add(p2_bd.multiply(BigDecimal.valueOf(0.5))));
        }
        
        for(int i = 0; i < order.size(); i++) {
        	st = new StringTokenizer(order.get(i));
        	
        	String child = st.nextToken();
        	String parent1 = st.nextToken();
        	String parent2 = st.nextToken();
        	
        	BigDecimal p1_bd = map.getOrDefault(parent1, BigDecimal.valueOf(0));
        	BigDecimal p2_bd = map.getOrDefault(parent2, BigDecimal.valueOf(0));
        	
        	map.put(child, p1_bd.multiply(BigDecimal.valueOf(0.5)).add(p2_bd.multiply(BigDecimal.valueOf(0.5))));
        }
        
        BigDecimal temp = BigDecimal.valueOf(-1);
        String name = "";
        for(int i = 0; i < M; i++) {
        	String str = br.readLine();
        	
        	if(!map.containsKey(str)) continue;
        	
        	BigDecimal now = map.get(str);

        	if(now.compareTo(temp) == 1) {
        		temp = now;
        		name = str;
        	}
        }
        
        System.out.println(name);
        
	}
}