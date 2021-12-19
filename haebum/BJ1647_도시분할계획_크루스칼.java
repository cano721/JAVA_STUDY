import java.io.*;
import java.util.*;

/**
 * MST로 다 연결한 다음
 * 가장 비용이 많이 든 마지막 연결집을 다른 마을로 분리
 * 마지막집을 연결했던 비용을 빼면 됨.
 * mst 알고리즘(크루스칼 버전)
 */
public class BJ1647_도시분할계획_크루스칼 {

    public static int n,m,answer,maxCost;
    public static int[] parent, rank;
    public static PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);

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
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parent = new int[n+1];
        rank = new int[n+1];

        for(int i = 1; i <= n ; i++){
            parent[i] = i;
            rank[i] = 0;
        }

        
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            q.offer(new Node(x,y,z));
        }

        kruskal();
        System.out.println(answer -maxCost);
    }

    public static void kruskal(){
        while(!q.isEmpty()){
            Node curNode = q.poll();

            int start = curNode.idx;
            int end = curNode.next;
            int cost = curNode.cost;

            if(find(start) != find(end)){
                union(start,end);
                answer += cost;
                maxCost = Math.max(cost,maxCost);
            }
        }
    }

    public static int find(int x){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y){
            return;
        }

        if(rank[x] < rank[y]){
            parent[x] = y;
        }else{
            parent[y] = x;
            if(rank[x] == rank[y]){
                rank[x]++;
            }
        }
    }
}
