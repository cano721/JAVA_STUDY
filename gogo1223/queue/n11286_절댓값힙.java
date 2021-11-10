package queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n11286_절댓값힙 {
	
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
        	@Override
        	public int compare(Integer o1, Integer o2) {
        		if(Math.abs(o1) == Math.abs(o2)) {
        			return o1-o2;
        		}
        		return Math.abs(o1)-Math.abs(o2);
        	}
		});
        
        for (int i = 0; i < N; i++) {
    	   int M = Integer.parseInt(br.readLine());
    	   
    	   if(M == 0) {
    		   if(priorityQueue.isEmpty()) {
    			   bw.write(0 + "\n");
    		   }else {
    			   bw.write(priorityQueue.poll() + "\n");
    		   }
    	   }else {
    		   priorityQueue.add(M);
    	   }
        }
        br.close();
        bw.close();

	}

}
