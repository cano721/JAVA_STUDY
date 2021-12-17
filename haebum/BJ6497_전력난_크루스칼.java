import java.io.*;
import java.util.*;

/**
 * 모든 집이 최소 거리로 연결되어 있어야 가장 적은 돈이 들어감.
 * mst 알고리즘(크루스칼 버전)
 */
public class BJ6497_전력난_크루스칼 {

    public static int n,m,allE,e;
    public static int[] parent, rank;
    public static PriorityQueue<Node> q;

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
        while(true){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            
            if(m == 0 && n == 0){
                break;
            }
            
            parent = new int[m];
            rank = new int[m];
            e = 0;
            allE = 0;

            for(int i = 0; i < m ; i++){
                parent[i] = i;
                rank[i] = 0;
            }

            q = new PriorityQueue<>(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1.cost > o2.cost){
                        return 1;
                    }else if(o1.cost < o2.cost){
                        return -1;
                    }
                    return 0;
                }
            });
            
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                q.offer(new Node(x,y,z));
                allE += z;
            }

            kruskal();
            System.out.println(allE - e);
        }
    }

    public static void kruskal(){
        while(!q.isEmpty()){
            Node curNode = q.poll();

            int start = curNode.idx;
            int end = curNode.next;
            long cost = curNode.cost;

            if(find(start) != find(end)){
                union(start,end);
                e += cost;
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
