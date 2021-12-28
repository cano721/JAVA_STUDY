package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] edgeCount =new int[N + 1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
        	graph.get(a).add(b);
        	edgeCount[b]++;
        }
        
        //큐 생성
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < edgeCount.length; i++) {
        	if (edgeCount[i] == 0) {
        		q.offer(i);
        	}
        }
        
        while(!q.isEmpty()) {
        	int stuNum = q.poll();
        	System.out.print(stuNum + " ");
        	
        	List<Integer> list = graph.get(stuNum);
        	for (int i = 0; i < list.size(); i++) {
        		int temp = list.get(i);
        		edgeCount[temp] -- ;
        		if(edgeCount[temp] == 0) {
        			q.offer(temp);
        		}
        	}
        }

	}

}
