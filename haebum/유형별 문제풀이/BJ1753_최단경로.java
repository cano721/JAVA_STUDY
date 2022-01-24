/*
    시작점에서 다른 모든 정점으로의 최단경로 구하기! ( 다익스트라)

*/

import java.util.*;
import java.io.*;

public class BJ1753_최단경로 {

    static class Node{
        int idx; // 노드번호
        int cost; // 노드번호까지 가는 비용

        // 생성자
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static int start,v,e;
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        start = Integer.parseInt(br.readLine());

        for(int i = 0; i < v+1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b,cost));
        }

        dist = new int[v+1];

        Arrays.fill(dist,Integer.MAX_VALUE);
        dijkstra();
        for(int i = 1; i < dist.length; i++){
            if(dist[i] == Integer.MAX_VALUE){
                bw.write("INF" + "\n");
            }else{
                bw.write(dist[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void dijkstra(){
        // 최소비용 기준 우선순위큐
        PriorityQueue<Node> q = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        dist[start] = 0;
        //시작지점 넣어주기
        q.offer(new Node(start,0));

        while(!q.isEmpty()){
            Node curNode = q.poll();

            // 현재노드까지 온 비용이 기존에 저장되어있던 비용보다 크다면
            // 다음작업들을 할 필요가 없음
            if(dist[curNode.idx] < curNode.cost){
                continue;
            }

            // 현재노드와 이어져 있는 간선들 확인
            for (int i = 0; i < graph.get(curNode.idx).size(); i++){
                // 뽑은 간선의 노드
                Node nextNode = graph.get(curNode.idx).get(i);

                // 다음노드를 현재노드에서 갈건데 이미 저장되어있는것보다 최소비용일때만 갈거임
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    q.offer(new Node(nextNode.idx,dist[nextNode.idx]));
                }
            }
        }
    }
}
