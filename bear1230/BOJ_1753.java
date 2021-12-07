import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int end, w;
    public Node(int end, int w) {
        this.end = end;
        this.w = w;
    }
    @Override
    public int compareTo(Node o) {
        return w - o.w;
    }
}

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int v,e,k;
    static List<Node>[] list;
    static int[] dist;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        
        list = new ArrayList[v+1];
        dist = new int[v+1];

        Arrays.fill(dist, INF);

        for(int i=1; i<=v; i++) {
            list[i] = new ArrayList<>();
        }

        // list에 그래프 정보 초기화
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, w));
        }

        StringBuilder sb = new StringBuilder();

        // 다익스트라
        dijkstra(k);

        for(int i=1; i<=v; i++) {
            if(dist[i] == INF) sb.append("INF\n");
            else sb.append(dist[i] + "\n");
        }

        System.out.println(sb.toString());

    }

    private static void dijkstra(int a) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] check = new boolean[v+1];
        queue.add(new Node(a, 0));
        dist[a] = 0;

        while(!queue.isEmpty()) {
            Node curNode = queue.poll();
            int cur = curNode.end;

            if(check[cur] == true) continue;
            check[cur] = true;

            for(Node node : list[cur]) {
                if(dist[node.end] > dist[cur] + node.w) {
                    dist[node.end] = dist[cur] + node.w;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }
}
