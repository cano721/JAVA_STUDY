/**
 * 시작지점에 시간이 준채로 돌아온다는건
 * 어딘가에 음의사이클이 존재하면 가능
 * 각 테스트케이스마다 음의 사이클이 존재하는지 확인
 * 벨만포드
 * 
 * 주의점
 * 1. 도로는 양방향
 * 2. 어느곳이서든 음의사이클이 존재하면 됨!
 */

import java.util.*;
import java.io.*;

public class BJ1865_웜홀 {
    
    public static int n,m,w;
    public static long[] dist;
    public static ArrayList<ArrayList<Node>> graph;

    static class Node{
        int next; // 다음 노드
        long cost; // 다음노드 가기위한 비용

        Node(int next, long cost){
            this.next= next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            for(int i = 0; i <= n; i++){
                graph.add(new ArrayList<>());
            }
            // 도로 담기
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                // 양방향
                graph.get(a).add(new Node(b,cost));
                graph.get(b).add(new Node(a,cost));
            }
            // 웜홀 담기
            for(int i = 0; i < w; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b,-cost));
            }

            //도시거리까지의 최소비용 저장배열
            dist = new long[n+1];
            Arrays.fill(dist,Integer.MAX_VALUE);

            bw.write(bellmanFord() ? "NO\n" : "YES\n");
        }
        bw.flush();
        bw.close();
    }

    public static boolean bellmanFord(){
        dist[1] = 0l;
        // (노드의 개수 -1 개) 반복
        for(int i = 0; i < n-1; i++){
            // 간선의 개수만큼 반복(각노드별 간선을 담아둠)
            for(int j =1; j <= n; j++){
                for(Node curNode : graph.get(j)){
                    // 다음노드 저장된값보다 현재 저장된값 + 다음노드가기위한 비용이 더 적으면 변경
                    if(dist[curNode.next] > dist[j] + curNode.cost){
                       dist[curNode.next] = dist[j] + curNode.cost;
                    }
                }
            }
        }
        // 모든 간선을 도시-1만큼 돌았지만 그 후에 또 바뀌는 값이 있으면 음의 사이클 존재
        // 간선의 개수만큼 반복(각노드별 간선을 담아둠)
        for(int j =1; j <= n; j++){
            for(Node curNode : graph.get(j)){
                if(dist[curNode.next] > dist[j] + curNode.cost){
                    return false;
                }
            }
        }

        return true;
    }
}
