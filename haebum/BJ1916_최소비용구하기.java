
/**
 * 출발도시와 도착 도시가 주어졌을때 최소비용 출력
 * 다익스트라 적용
 */

import java.util.*;
import java.io.*;

public class BJ1916_최소비용구하기 {
    
    public static int n,m;
    public static int[] dist;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static class Node{
        int idx;
        int cost;

        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        // 도시거리까지의 최소비용 저장배열
        dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        // 도시개수만큼 만들어두기
        for(int i = 0; i <= n; i ++){
            graph.add(new ArrayList<Node>());
        }
        StringTokenizer st;
        // 버스 수 만큼 그래프 채우기
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int answer = dijkstra(start, end);

        System.out.println(answer);
    }

    public static int dijkstra(int start,int end){
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        dist[start] = 0;
        q.offer(new Node(start,0));

        while(!q.isEmpty()){
            Node curNode = q.poll();

            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            for(int i = 0; i < graph.get(curNode.idx).size(); i++){
                Node nextNode = graph.get(curNode.idx).get(i);
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    q.offer(new Node(nextNode.idx,dist[nextNode.idx]));
                }
            }
        }

        return dist[end];
    }
}
