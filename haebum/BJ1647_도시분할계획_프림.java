import java.io.*;
import java.util.*;

/**
 * MST로 다 연결한 다음
 * 가장 비용이 많이 든 마지막 연결집을 다른 마을로 분리
 * 마지막집을 연결했던 비용을 빼면 됨.
 * mst 알고리즘(프림 버전)
 */
public class BJ1647_도시분할계획_프림 {

    public static int m,n,answer,maxCost;
    public static ArrayList<Node>[] a;
    public static boolean[] visited;

    static class Node{
        int idx;
        int next;
        int cost;
        Node(int idx, int next, int cost){
            this.idx = idx;
            this.next = next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        a = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            a[i] = new ArrayList<Node>();
        }

        visited = new boolean[n+1];
        
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            a[x].add(new Node(x,y,z));
            a[y].add(new Node(y,x,z));
        }

        prim();
        System.out.println(answer-maxCost);
    }

    public static void prim(){
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        Deque<Integer> dq = new ArrayDeque<>();

        dq.offer(1);
        visited[1] = true;
        while(!dq.isEmpty()){
            int nodeIdx = dq.poll();
            for(int i = 0; i < a[nodeIdx].size(); i++){
                Node curNode = a[nodeIdx].get(i);

                if(visited[curNode.next] == false){
                    q.offer(curNode);
                }
            }

            while(!q.isEmpty()){
                Node curNode = q.poll();

                if(visited[curNode.next] == false){
                    visited[curNode.next] = true;
                    answer += curNode.cost;
                    maxCost = Math.max(maxCost,curNode.cost);
                    dq.offer(curNode.next);
                    break;
                }
            }
        }
    }
}
