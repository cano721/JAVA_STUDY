package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import test.Main.Bus;

public class Main{
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        
        ArrayList<HashMap<String, Integer>> link = new ArrayList<HashMap<String, Integer>>();
        
        for(int i = 0; i <= N; i++) {
        	link.add(new HashMap<String, Integer>());
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	String a = st.nextToken();
        	String b = st.nextToken();
        	int c = Integer.parseInt(st.nextToken());
        	
        	HashMap<String, Integer> map1 = link.get(Integer.parseInt(a));
        	HashMap<String, Integer> map2 = link.get(Integer.parseInt(b));
        	
        	int temp1 = map1.getOrDefault(b, -1);
        	int temp2 = map1.getOrDefault(a, -1);
        	
        	if(temp1 == -1) map1.put(b, c);
        	else if(temp1 < c) map1.put(b,  c);
        	
        	if(temp2 == -1) map2.put(a, c);
        	else if(temp2 < c)map2.put(a, c);
        }
        
        String[] AB = br.readLine().split(" ");
        int A = Integer.parseInt(AB[0]);
        int B = Integer.parseInt(AB[1]);
        
        PriorityQueue<Bus> pq = new PriorityQueue<Bus>(new Comparator<Bus>() {

			@Override
			public int compare(Bus o1, Bus o2) {
				return Integer.compare(o2.min, o1.min);
			}
        	
        });
        
        int[] isvisited = new int[N+1];        
        
        pq.add(new Bus(A, Integer.MAX_VALUE));
        isvisited[A] = Integer.MAX_VALUE;

        
        while(!pq.isEmpty()) {
        	Bus now = pq.poll();
        	
        	if(now.now == B) {
        		System.out.println(now.min);
        		return;
        	}
        	
        	HashMap<String, Integer> map = link.get(now.now);
        	
        	for(String key : map.keySet()) {
        		int to = Integer.parseInt(key);
        		int min = Math.min(now.min, map.get(key));
        		
        		if(isvisited[to] < min) {
        			isvisited[to] = min;
        			pq.add(new Bus(to, min));
        		}
        	}
        }   
    }
    static class Bus{
    	int now;
    	int min;
    	
    	public Bus(int now, int min){
    		this.now = now;
    		this.min = min;
    	}
    }
    
    static boolean[] copy(boolean[] bool) {
    	boolean[] temp = new boolean[bool.length];
    	
    	for(int i = 0; i < temp.length; i++) {
    		temp[i] = bool[i];
    	}
    	
    	return temp;
    }
}
