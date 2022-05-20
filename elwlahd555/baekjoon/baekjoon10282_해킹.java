package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baekjoon10282_해킹 {

    private static int n,d,c;
    private final static int INF = (int) 1e9;
    private static ArrayList<ArrayList<Node>> graph;
    private static int[] dis = new int[10001];

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(reader.readLine());

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(reader.readLine());

            n = Integer.parseInt(st.nextToken());//컴퓨터 개수
            d = Integer.parseInt(st.nextToken());//의존성 개수
            c = Integer.parseInt(st.nextToken());//해킹당한 컴퓨터 번호

            graph = new ArrayList<>();

            for (int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i=0; i<d; i++) {
                st = new StringTokenizer(reader.readLine());

                int a = Integer.parseInt(st.nextToken());//컴퓨터 a가
                int b = Integer.parseInt(st.nextToken());//컴퓨터 b를 의존
                int s = Integer.parseInt(st.nextToken());//b가 감염되면 s초후 a도 감염됨

                graph.get(b).add(new Node(a, s));
            }

            Arrays.fill(dis,INF);

            dijkstra(c);

            int cnt = 0;
            int result = 0;
            for (int i=1; i<=n; i++) {
                if (dis[i] != INF) {
                    cnt++;
                    result = Math.max(result, dis[i]);
                }
            }

            sb.append(cnt + " " + result + "\n");

        }

        System.out.print(sb);

    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dis[start] = 0;

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            int dist = node.distance;
            int now = node.index;

            if (dis[now] < dist) {
                continue;
            }

            for (int i=0; i<graph.get(now).size(); i++) {
                int cost = dis[now] + graph.get(now).get(i).getDistance();

                if (cost < dis[graph.get(now).get(i).getIndex()]) {
                    dis[graph.get(now).get(i).getIndex()] = cost;
                    pq.add(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }

        }

    }//dijkstra

    static class Node implements Comparable<Node> {
        int index, distance;
        Node (int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        private int getIndex() {
            return this.index;
        }

        private int getDistance() {
            return this.distance;
        }

        @Override
        public int compareTo(Node o) {
            if (this.distance < o.distance) {
                return -1;
            }
            return 1;
        }
    }
}