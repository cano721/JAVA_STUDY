
/**
 * 음수가 존재하는 시작지점부터 도착지점까지 가기
 * 벨만포드!
 */

import java.util.*;
import java.io.*;

public class BJ11657_타임머신 {
    
    public static int n,m;
    public static long[] dist;
    public static Node[] graph;

    static class Node{
        int idx; // 현재 노드
        int next; // 다음 노드
        int cost; // 다음노드 가기위한 비용

        Node(int idx,int next, int cost){
            this.idx = idx;
            this.next= next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 모든 간선 담을 그래프
        graph = new Node[m];

        // 도시거리까지의 최소비용 저장배열
        dist = new long[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        
        // 버스 수 만큼 그래프 채우기
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[i] = new Node(a,b, cost);
        }

        //음의 사이클이 있을경우
        if(!bellmanFord()){
            bw.write(-1 + "\n");
        }else{
            //없으면 2번부터 출력
            for(int i = 2; i <= n; i++){
                bw.write(dist[i] == Integer.MAX_VALUE ? -1+"\n" : dist[i]+"\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static boolean bellmanFord(){
        dist[1] = 0l;
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < m; j++){
                // 현재 노드
                Node curNode = graph[j];

                // 현재노드에 저장된값이 무한대가 아니면서
                // 다음노드 저장된값보다 현재 저장된값 + 다음노드가기위한 비용이 더 적으면 변경
                if(dist[curNode.idx] != Integer.MAX_VALUE && dist[curNode.next] > dist[curNode.idx] + curNode.cost){
                    dist[curNode.next] = dist[curNode.idx] + curNode.cost;
                }
            }
        }
        // 모든 간선을 도시-1만큼 돌았지만 그 후에 또 바뀌는 값이 있으면 음의 사이클 존재
        for(int j = 0; j < m; j++){
            Node curNode = graph[j];

            if(dist[curNode.idx] != Integer.MAX_VALUE && dist[curNode.next] > dist[curNode.idx] + curNode.cost){
                return false;
            }
        }

        return true;
    }
}
