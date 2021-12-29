package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2623_음악프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }
        
        int[] indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int K = Integer.parseInt(st.nextToken());
        	int before = Integer.parseInt(st.nextToken());
        	for (int j = 1; j < K; j++) {
        		int singer = Integer.parseInt(st.nextToken());
                a.get(before).add(singer);
                indegree[singer]++;
 
                before = singer;
			}
		}
        //위상정렬
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
 
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);
 
            for (int next : a.get(now)) {
                indegree[next]--;
 
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        if (result.size() != N) {	//cycle이 발생한 경우
            System.out.println(0);
        }
 
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
	}

}
