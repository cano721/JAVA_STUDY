package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n1766_문제집 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }
        int[] indegree = new int[N+1];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	a.get(A).add(B);
        	indegree[B]++;
		}
        //위상정렬 알고리즘
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
    		int now = q.poll();
            for (int next : a.get(now)) {
                if (--indegree[next] == 0) {
                    q.offer(next);
                }
            }
            System.out.print(now + " ");
        }
	}

}
