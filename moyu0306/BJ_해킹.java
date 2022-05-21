import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<ArrayList<int[]>> adjList;
    static int[] dist;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int d = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            mkAdjList(n,d,br);
            dijkstra(n,c,sb);
        }
        System.out.println(sb.toString());
    }
    public static void mkAdjList(int n, int d , BufferedReader br) throws IOException {
        adjList = new ArrayList<>();
        for(int i=0; i<n+1; i++) adjList.add(new ArrayList<>());
        for(int i=0; i<d; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine()," ");
            int b = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            adjList.get(a).add(new int[]{b,t});
        }
    }

    public static void dijkstra(int n, int c, StringBuffer sb){
        int count = 0;
        int time = 0;
        dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[c] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{return a[1]- b[1];});
        pq.offer(new int[]{c,0});
        while(!pq.isEmpty()){
            int[] node = pq.poll();
            if(node[1]>dist[node[0]]) continue;
            for(int[] edge : adjList.get(node[0])){
                int nextNode = edge[0];
                int cost = edge[1];
                if(dist[nextNode]> node[1]+cost){
                    dist[nextNode] = node[1]+ cost;
                    pq.offer(new int[]{edge[0],dist[nextNode]});
                }
            }
        }

        for(int i=0; i<n+1; i++){
            if(dist[i]!=Integer.MAX_VALUE){
                count++;
                time = Integer.max(time,dist[i]);
            }
        }
        sb.append(count);
        sb.append(" ");
        sb.append(time);
        sb.append("\n");
    }
}