package cindya.bj1922_네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 연결 비용 클래스
class Edge implements Comparable<Edge>{
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    // 우선순위큐에서 비용을 기준으로 비교하도록 하는 함수
    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

public class Main {
    private static int[] parent, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()), m = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        parent = new int[n + 1];
        depth = new int[n + 1];
        int totalCost = 0;

        // 자기자신을 부모로 초기화
        for(int i = 0; i <= n; i++)
            parent[i] = i;

        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges.offer(new Edge(Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken(" ")), Integer.parseInt(st.nextToken())));
        }
        br.close();

        // 연결비용 루프
        while (!edges.isEmpty()){
            Edge edge = edges.poll();
            int fp = findParent(edge.from), tp = findParent(edge.to); // 연결되는 두 컴퓨터의 최상위 부모를 찾음
            if(fp != tp) { // 둘의 최상위 부모가 같지 않으면
                // 자식이 더 많은 쪽으로 합침
                if(depth[fp] < depth[tp]){
                    parent[fp] = tp;
                    depth[tp] += depth[fp];
                }
                else {
                    parent[tp] = fp;
                    depth[fp] += depth[tp];
                }
                totalCost += edge.cost; // 비용 합산
            }
        }
        System.out.println(totalCost);
    }

    // 최상위 부모를 찾는 함수
    private static int findParent(int child){
        if(child != parent[child]) // child의 부모가 자기 자신이 아니면
            parent[child] = findParent(parent[child]); // 최상위 부모를 찾아 child의 부모로 저장
        return parent[child];
    }
}
