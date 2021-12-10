import java.util.*;
import java.io.*;
/**
 *  https://www.acmicpc.net/board/view/50494 해당 질문글을 읽고 풀었습니다.
 *  문제에서 요구하는건 음의 사이클의 존재여부.. 즉 어떤 정점에서 출발하든 상관없이 음의 싸이클이 존재하기만 하는지 확인하면됨.
 *  2가지 풀이법이 존재한다.
 *  1번, 모든 정점을 방문하면서 음의 사이클이 존재하는지 확인하는 방법
 *  --> 일반적인 벨만 포드 알고리즘을 사용하는 경우에 현재 확인하는 노드가 INF거나 끊어진 경우 (갈 수 없는 경우) 
 *      해당 노드를 확인하지 않는 특성이 있다. (벨만포드는 출발지 - 목적지의 최단거리를 구하는 알고리즘이기 때문에)
 *      따라서 일반적인 벨만 포드 알고리즘을 사용하면 출발지에서 갈 수 없는 노드들의 사이클도 고려해주어야 하기 떄문에 
 *      모든 정점을 한 번씩 출발지로 해서 음의 사이클이 존재하는지 확인해야한다.
 * 
 *  2번, 끊어진 것 상관없이 모든 노드들을 방문하여 사이클이 존재하는지 확인한다.
 *  --> 모든 노드의 거리를 INF로 둘 필요도 없다. 최단거리를 구할 필요도 없고 단지 음의 사이클이 존재하는지만 구하면 되는 문제.
 *      출발지를 어디로 설정하든지 상관없고 벨만포드 알고리즘을 V번 반복했을 때 update되는 간선이 존재하는지 확인만 하면 된다.
 */
public class Main {
    
    static class Edge{
        int to, weight;
        public Edge(int t, int w){
            to =t;
            weight = w;
        }
    }

    static int tc, N, M, W;
    static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
       
        input();
        
    }

    static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       tc = Integer.parseInt(br.readLine());
       StringBuilder sb = new StringBuilder();
       
       while(tc-- > 0){
           StringTokenizer st = new StringTokenizer(br.readLine());
           N = Integer.parseInt(st.nextToken());
           M = Integer.parseInt(st.nextToken());
           W = Integer.parseInt(st.nextToken());

           edges = new ArrayList[N+1];
           for(int i =1; i<= N; i++) edges[i] = new ArrayList<>();

           for(int i =0; i<M; i++){
                st= new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[from].add(new Edge(to, w));
                edges[to].add(new Edge(from, w));
            }

            for(int i =0; i<W; i++){
                st= new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[from].add(new Edge(to,-w));
            }

            if(!pro()) sb.append("YES\n");
            else sb.append("NO\n");
           
       } 
       System.out.println(sb);
    }


    static boolean pro(){
        int[] dist = new int[N+1];
   
        for(int i =0; i<N; i++){
            for(int j =1; j<=N; j++){
                for(Edge edge : edges[j]){
                    if(dist[j] + edge.weight < dist[edge.to]){
                        dist[edge.to] = dist[j] + edge.weight;
                        if(i == N-1) return false;
                    }
                }
            }
        }
        return true;
    }
}
