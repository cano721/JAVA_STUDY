import java.util.*;
import java.io.*;

public class Main {
    
    static class Edge{
        int to, weight;
        public Edge(int t, int w){
            to =t;
            weight =w;
        }
    }

    static class Info{
        int number, dist;
        public Info(int n, int d){
            number = n;
            dist = d;
        }
    }

    static int v,e,k;
    static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
       
       input();
       pro();
    }

    public static void input() throws Exception{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        edges = new ArrayList[v+1];
        for(int i =0; i<=v; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i =0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, w));
        }


    }

    public static void pro(){
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[k] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        pq.add(new Info(k, 0));

        while(!pq.isEmpty()){
            Info info = pq.poll();

            if(info.dist > dist[info.number]) continue;
            dist[info.number] = info.dist;
            for(Edge edge : edges[info.number]){
                if(dist[edge.to] < info.dist + edge.weight) continue;
                dist[edge.to] = info.dist + edge.weight;
                pq.add(new Info(edge.to, dist[edge.to]));
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int i =1; i<=v; i++){
            if(dist[i] == Integer.MAX_VALUE) ans.append("INF\n");
            else ans.append(dist[i] + "\n");
        }
        System.out.println(ans);
    }



    
}
   

