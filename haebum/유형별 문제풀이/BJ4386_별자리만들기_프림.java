import java.io.*;
import java.util.*;

/**
 * 각 좌표별 별들을 저장해두고
 * 별들끼리 비교하여 간선 생성(cost는 두 좌표 사이의 거리)
 * 간선을 만든 후부터는 mst 실행
 * 프림방식
 */
public class BJ4386_별자리만들기_프림 {

    //간선(현재좌표,다음좌표,비용)
    static class Edge{
        int idx;
        int next;
        double cost;
        Edge(int idx, int next, double cost){
            this.idx = idx;
            this.next = next;
            this.cost = cost;
        }
    }

    //별(x좌표,y좌표)
    static class Star{
        double x;
        double y;
        Star(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    public static int n;
    public static double answer;
    public static ArrayList<Star> stars = new ArrayList<>(); // 별들을 담아둘 집합
    public static ArrayList<Edge>[] edges;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        edges = new ArrayList[n];
        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
        StringTokenizer st;
        //별들 위치 추가
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            stars.add(new Star(x,y));
        }

        //간선 추가하기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    continue;
                }
                Star starA = stars.get(i);
                Star starB = stars.get(j);

                double cost = Math.sqrt(Math.pow((starA.x - starB.x),2) + Math.pow((starA.y - starB.y),2));
                edges[i].add(new Edge(i, j, cost));
            }
        }

        prim();
       
        bw.write(String.format("%.2f", answer) +"\n");
        bw.flush();
        bw.close();
    }

    public static void prim(){
        PriorityQueue<Edge> q = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.cost < o2.cost){
                    return -1;
                }else if(o1.cost > o2.cost){
                    return 1;
                }else return 0;
            }
        });

        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);
        while(!dq.isEmpty()){
            int nodeIdx = dq.poll();
            visited[nodeIdx] = true;

            for(int i = 0; i < edges[nodeIdx].size(); i++){
                Edge curEdge = edges[nodeIdx].get(i);
                if(visited[curEdge.next] == false){
                    q.offer(curEdge);
                }
            }

            while(!q.isEmpty()){
                Edge curEdge = q.poll();
                if(visited[curEdge.next] == false){
                    visited[curEdge.next] = true;
                    answer += curEdge.cost;
                    dq.offer(curEdge.next);
                    break;
                }
            }
        }
    };
}
