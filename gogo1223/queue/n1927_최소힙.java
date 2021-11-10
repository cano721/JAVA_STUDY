package queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class n1927_최소힙 {

	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
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
