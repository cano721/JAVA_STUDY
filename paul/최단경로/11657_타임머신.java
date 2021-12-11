import java.util.*;
import java.io.*;

public class Main {
    
    static class Edge{
        int to, weight;
        public Edge(int t, int w){
            to = t;
            weight =w;
        }
    }


    static int N, M;
    static ArrayList<Edge>[] edges;

    public static void main(String[] args) throws Exception {
       
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N+1];
        for(int i =1; i<=N; i++) edges[i] = new ArrayList<>();
        for(int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            edges[from].add(new Edge(to, t));
        }
    }

    static void pro(){
        long[] dist = new long[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        boolean flag = false;
        for(int i =0; i< N; i++){
            for(int j = 1; j<=N; j++ ){
                for(Edge edge : edges[j]){
                    if(dist[j] == Integer.MAX_VALUE) continue;
                    if(dist[j] + edge.weight < dist[edge.to] ){
                        dist[edge.to] = dist[j] + edge.weight;
                        if(i == N-1) flag = true;
                    }
                }
            }
        }


        if(flag) System.out.println(-1);
        else{
            StringBuilder sb = new StringBuilder();
            for(int i= 2; i<=N; i++){
                if(dist[i] == Integer.MAX_VALUE) sb.append(-1 + "\n");
                else sb.append(dist[i] + "\n");
            }
            System.out.println(sb.toString());
        }
        
    }
}


