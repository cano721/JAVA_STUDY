package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n14567_선수과목 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }
        int[] parent = new int[N+1];
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	a.get(A).add(B);
        	parent[B]++;
		}
        //위상정렬 알고리즘
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) {
                q.offer(i);
            }
        }
        
        int[] result = new int[N + 1];
        int semester = 1;
        while (!q.isEmpty()) {
        	int size = q.size();
        	while(size-- != 0) {	//q size 1일 때까지
        		int now = q.poll();
                result[now] = semester;
                for (int next : a.get(now)) {
                    if (--parent[next] == 0) {
                        q.offer(next);
                    }
                }
        	}
            semester++;
        }
        
        // 특정 건물을 짓는 데 걸린 시간을 출력.
        for (int i = 1; i <= N; i++) {
            System.out.print(result[i] +" ");
        }
	}

}
