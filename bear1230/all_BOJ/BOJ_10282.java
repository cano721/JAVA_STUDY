import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to, weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight  = weight;
        }
    }
    static int t;
    static int[] dist;
    static List<List<Node>> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist = new int[n+1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            list = new ArrayList<>();
            for(int j = 0; j <= n; j++){
                list.add(new ArrayList<>());
            }

            for(int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new Node(a, s));
            }

            dijkstra(n, c);

            int num = 0;
            int time = Integer.MIN_VALUE;
            for(int j=1; j <=n; j++) {
                if(dist[j] != Integer.MAX_VALUE) {
                    num++;
                    time = Math.max(time, dist[j]);
                }
            }

            System.out.println(num+" "+time);
        }
    }

    public static void dijkstra(int n, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        });
        boolean[] visited = new boolean[n+1];

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.to] = true;
            
            for(Node nNode: list.get(node.to)) {
                if(visited[nNode.to]) continue;

                if(dist[nNode.to] > dist[node.to] + nNode.weight) {
                    dist[nNode.to] = dist[node.to] + nNode.weight;
                    pq.offer(new Node(nNode.to, dist[nNode.to]));
                }
            }
        }
    }
}
