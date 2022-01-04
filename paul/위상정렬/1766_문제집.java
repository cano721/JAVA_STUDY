import java.io.*;
import java.util.*;
/**
 *  queue -> priorityQueue
 */
public class Main{
    static int n, m;
    static List<Integer>[] list;
    static int[] indegree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n+1];
        list = new ArrayList[n+1];
        for(int i=0; i < n+1; i++) list[i] = new ArrayList<>();
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);
            indegree[B]++;
        }

        pro();
    }

    static void pro(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =1; i< n+1; i++){
            if(indegree[i] == 0 ) pq.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int now = pq.poll();
            sb.append(now + " ");
            for(int next : list[now]){
                if(--indegree[next] == 0) pq.add(next);
            }
        }
        
        System.out.println(sb);
    }
}