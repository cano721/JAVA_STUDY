import java.util.*;
import java.io.*;

/**
 * 최소비용으로 모든 컴퓨터 연결하기 MST
 * 크루스칼 버전
 */
public class BJ1922_네트워크연결_크루스칼 {

    public static int n,m;
    public static int[] parent,rank;
    public static long answer;
    public static PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);

    static class Node{
        int x;
        int y;
        int cost;
        Node(int x,int y,int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        rank = new int[n+1];
        for(int i = 1; i<=n; i++){
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            q.offer(new Node(start,end,cost));
            q.offer(new Node(end,start,cost));
        }
        kruskal();
        System.out.println(answer);
    }

    public static void kruskal(){
        answer = 0;
        while(!q.isEmpty()){
            Node curNode = q.poll();

            int start = curNode.x;
            int end = curNode.y;
            int cost = curNode.cost;

            if(find(start) != find(end)){
                union(start, end);
                answer += cost;
            }
        }
    }

    public static int find(int x){
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;

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
