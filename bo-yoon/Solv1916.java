package argo.study.dijkstra;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solv1916 {
    static int N, M;
    static int startPoint, destination;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> arr;

    public static void main(String[] args) throws IOException {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N + 1; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr.get(s).add(new Node(e, w));
        }
        st = new StringTokenizer(br.readLine());
        startPoint = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());

        //output
        int res = dijkstra(startPoint, destination);
        bw.write(res + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[N + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int pos = current.end;


            if (!visited[pos]) {
                visited[pos] = true;

                for (Node node : arr.get(pos)) {
                    if (!visited[node.end] && dist[node.end] > dist[pos] + node.cost) {
                        dist[node.end] = dist[pos]
                                + node.cost;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist[end];

    }

}

class Node implements Comparable<Node> {
    int end, cost;

    public Node(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node n) {
        return this.cost - n.cost;
    }
}
