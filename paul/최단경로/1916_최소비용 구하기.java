import java.util.*;
import java.util.Map.Entry;
import java.io.*;
/**
 *  57번 라인 if(node.weight + cur.weight >= dist[node.num]) continue; .. >= 잊지말자...
 */
public class Main {
    
    static class Node{
        int weight, num;
        public Node(int n, int w){
            num = n;
            weight = w;
        }
    }

    static int N, M, start, dest;
    static ArrayList<Node>[] edges;
    static int[] dist;

    public static void main(String[] args) throws Exception {
       input();
       int ans = dijkstra(start);
       System.out.println(ans);
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new ArrayList[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i = 0; i<=N; i++) edges[i] = new ArrayList<>();
        for(int i =0; i<M; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[from].add(new Node(to, w));
        }

        StringTokenizer st= new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
    }

    static int dijkstra(int start){
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1,o2) -> o2.weight - o1.weight );
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.weight > dist[cur.num]) continue;
            for(Node node : edges[cur.num]){   
                if(node.weight + cur.weight >= dist[node.num]) continue;
                dist[node.num] = node.weight + cur.weight;
                pq.add(new Node(node.num, dist[node.num]));
            }
        }
        return dist[dest];
    }    
}


