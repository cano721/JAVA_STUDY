import java.util.*;
import java.io.*;

/* 
    이분탐색으로 중량을 정한 후
    (log10^8)
    다리를 건널 수 있는지 확인
    (bfs 최대 간선만큼 확인)

    건널 수 있다면 무게를 늘리기
    못건넌다면 무게 줄이기
*/

public class BJ1939_중량제한 {

    static class Edge{
        int next;
        int cost;
        Edge(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }

    public static int n,m,max,answer,factA,factB;
    public static ArrayList<Edge>[] land;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        land = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            land[i] = new ArrayList<>();
        }

        max = 0;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            land[a].add(new Edge(b,cost));
            land[b].add(new Edge(a,cost));
            max = Math.max(cost,max);
        }

        st = new StringTokenizer(br.readLine());
        factA = Integer.parseInt(st.nextToken());
        factB = Integer.parseInt(st.nextToken());
        
        binary_search();
        System.out.println(answer);
    }

    public static void binary_search(){
        int start = 1;
        int end = max;

        while(start <= end){
            int mid = (start + end) /2;
            // 건널 수 없으면 무게 줄이기
            if(bfs(mid) == false){
                end = mid-1;
            // 건널 수 있으면 무게 늘리기(최대 무게 구해야해서 정답 여기서 구함)
            }else{
                start = mid+1;
                answer = mid;
            }
        }
    }

    // 도착지까지 갈 수 있는지 체크
    public static boolean bfs(int weight){
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[n+1];

        q.offer(factA);
        visited[factA] = true;

        while(!q.isEmpty()){
            int curLand = q.poll();
            // 도착지 도착
            if(curLand == factB){
                return true;
            }  
            // 이어져있는 간선 중 현재 무게를 견딜 수 있는것만 추가
            for(int i = 0; i < land[curLand].size(); i++){
                Edge nextLand = land[curLand].get(i);
                if(nextLand.cost >= weight && visited[nextLand.next] == false){
                    q.offer(nextLand.next);
                    visited[nextLand.next] = true;
                }
            }
        }
        return false;
    }
}