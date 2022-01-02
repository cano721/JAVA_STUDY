package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n1516_게임개발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }
 
        int[] indegree = new int[N + 1]; // 특정 건물을 짓기 전에 먼저 지어야 할 건물의 개수
        int[] times = new int[N + 1]; // 특정 건물을 짓는 데 걸리는 시간
 
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
 
            times[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int num = Integer.parseInt(st.nextToken());
 
                if (num == -1) {
                    break;
                }
 
                a.get(num).add(i);
 
                indegree[i]++;
            }
        }
        //위상정렬
        Queue<Integer> q = new LinkedList<>();
        
        // 먼저 지어야할 건물이 없는 건물을 큐에 집어 넣음.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        
        // 특정 건물을 짓기 전까지 걸린 시간
        int[] result = new int[N + 1];
 
        while (!q.isEmpty()) {
            int now = q.poll();
 
            for (int next : a.get(now)) {
                indegree[next]--;
                
                // next 건물을 짓기 전까지 걸린 시간 계산.
                result[next] = Math.max(result[next], result[now] + times[now]);
 
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        
        // 특정 건물을 짓는 데 걸린 시간을 출력.
        for (int i = 1; i <= N; i++) {
            System.out.println(result[i] + times[i]);
        }
 
	}

}
